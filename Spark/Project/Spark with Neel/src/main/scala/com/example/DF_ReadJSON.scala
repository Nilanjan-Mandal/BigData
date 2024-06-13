package com.example

import org.apache.spark.sql.SparkSession

object DF_ReadJSON extends App {

  val spark = SparkSession
    .builder()
    .appName("JSON")
    .master("local[2]")
    .getOrCreate()

  val json_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/unece.json"

  // Load JSON File into DataFrame
  val df = spark.read
    .format("json")
    .option("mode", "PERMISSIVE")
    // .option("mode", "DROPMALFORMED")
    // .option("mode", "FAILFAST")
    .load(json_path)

  // Display the JSON Records
  df.printSchema()
  df.show()

  // Display all the JSON Records
  df.show(false)

  df.explain()
  scala.io.StdIn.readLine()
  spark.stop()
}

