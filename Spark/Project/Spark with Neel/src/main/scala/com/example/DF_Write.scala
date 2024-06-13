package com.example

import org.apache.spark.sql.{SaveMode, SparkSession}

object DF_Write extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Write to sink")
    .getOrCreate()

  // PATH
  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/orders.csv"

  // Load CSV File into DataFrame
  val df = spark.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load(csv_path)

  //Display the CSV Records
  df.show()

  // Perform Repartition & SQL Operations
  val ordersdf = df
    .repartition(4)
    .where("order_customer_id > 10000")
    .select("order_id", "order_customer_id")
    .groupBy("order_customer_id")
    .count()

  ordersdf.show(false)

  val orders_write = ordersdf.write
    .format("json")
    .mode(SaveMode.Append)
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/OutPut")
    .save()

  df.explain(true)
  scala.io.StdIn.readLine()
  spark.stop()
}
