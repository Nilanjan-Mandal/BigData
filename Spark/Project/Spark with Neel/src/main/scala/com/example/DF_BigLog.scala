package com.example

import org.apache.spark.sql.SparkSession

object DF_BigLog extends App {

  // SetUp SparkSession
  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("BigLog")
    .getOrCreate()

  // In order to transform to DataFrame from RDD
  import spark.implicits._

  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/bigLog.txt"


  // Stop SparkSession
  spark.stop()
}
