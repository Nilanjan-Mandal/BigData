package com.theory

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}

object Dataframe extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val sparkConf = new SparkConf()
  sparkConf.set("spark.app.name", "Create DataFrame")
  sparkConf.set("spark.master", "local[*]")
  val spark = SparkSession
    .builder()
    .config(sparkConf)
    .getOrCreate()

  val ordersDf = spark.read
    .format("csv")
    .option("header", true)
    .option("inferSchema", true)
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/orders.csv")
    .load()

  ordersDf.show(10)
  //ordersDf.printschema

  print(ordersDf.rdd.getNumPartitions)

  val groupordersDF = ordersDf
    .repartition(4)
    .where("order_customer_id > 10000")
    .select("order_id", "order_customer_id")
    .groupBy("order_customer_id")
    .count()

  groupordersDF.show(100)


  groupordersDF.write
    .format("csv")
    .mode(SaveMode.Append)
    .option("path", "/Users/nilanjan/Desktop/test")
    .save()

  val repGrouprdd = groupordersDF.repartition(4)

  Logger.getLogger("getClass.getName").info("My App has completed successfully")

  scala.io.StdIn.readLine()
  spark.stop()
}


