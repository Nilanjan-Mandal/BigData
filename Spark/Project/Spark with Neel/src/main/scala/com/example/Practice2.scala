package com.example

import org.apache.spark.sql.SparkSession

object Practice2 extends App {

  val spark = SparkSession
    .builder()
    .appName("Practice")
    .master("local[1]")
    .getOrCreate()

  val path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/flights.csv"

  val df = spark.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .option("mode", "PERMISSIVE")
    .load(path)

  df.createOrReplaceTempView("Flights")
  spark.sql("SHOW TABLES").show()
  val df_show = spark.sql("select * from flights where AIRLINE = 'AS'")

  val final_df = df_show.write
    .format("csv")
    .option("path", "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/OutPut")
    .mode("append")
    .save()


  spark.stop()
}
