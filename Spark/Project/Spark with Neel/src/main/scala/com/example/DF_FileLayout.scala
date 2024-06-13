package com.example

import org.apache.spark.sql.{SaveMode, SparkSession}

object DF_FileLayout extends App {

  val spark = SparkSession
    .builder()
    .enableHiveSupport()
    .master("local[*]")
    .appName("Partition")
    .getOrCreate()

  // Load The file
  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/orders.csv"

  // Read The file
  val df_read = spark.read
    .format("csv")
    .option("Header", "true")
    .option("inferSchema", "true")
    .load(csv_path)

  // Transformations
  val df_trans = df_read
      .where("order_status = 'CLOSED' ")

  df_trans.show(false)

  print("The current Partition is "+df_read.rdd.getNumPartitions )
  print("We will make 5 partition of the file")
  val Rep_df = df_read.repartition(5)
  print("The current Partition is "+df_read.rdd.getNumPartitions )

  // Write to a sink
  val df_write = Rep_df.write
    .mode(SaveMode.Overwrite)
    //.format("parquet")
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/OutPut1")
    .save()


  // We will do PartitionBy
  val df_write2 = df_read.write
    .mode(SaveMode.Overwrite)
    .format("csv")
    .partitionBy("order_status")
    //.partitionBy("order_status","")
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/OutPut2")
    .save()

  // We will do maxRecordsPerFile
  val df_write3 = df_read.write
    .mode(SaveMode.Overwrite)
    .format("csv")
    .partitionBy("order_status")
    .option("maxRecordsPerFile", 2000)
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/MaxRecords")
    .save()

  spark.sql("create database if not exists retail")

  // Save as a table
  // We may have to add the HIVE jars to use the HIVE metadata. spark hive 2.4.4 2.11

  val df_write4 = df_trans.write
    .mode(SaveMode.Overwrite)
    .format("csv")
    //.saveAsTable("closed_order")
    .saveAsTable("retail.orders")

  spark.catalog.listTables("retail").show()


  val df_write5 = df_trans.write
    .mode(SaveMode.Overwrite)
    .format("csv")
    .bucketBy(4,"order_customer_id")
    .sortBy("order_customer_id")
    .saveAsTable("retail.orders")
  spark.stop()
}
