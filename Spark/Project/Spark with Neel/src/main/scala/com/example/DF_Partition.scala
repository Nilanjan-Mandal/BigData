package com.example

import org.apache.spark.sql.SparkSession

object DF_Partition extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Partition")
    .getOrCreate()

  // Load The file
  val csv_path = "/Users/nilanjan/Documents/IT/BigData/Spark/Project/Files/Performance.csv"

  val df_read = spark.read
    .format("csv")
    .option("Header", "true")
    .option("inferSchema", "true")
    .load(csv_path)

  print("Number of Partition is: "+ df_read.rdd.getNumPartitions )

  // Let's do a repartition
  val Rep_df = df_read.repartition(5)
  print("Number of Partition is: "+ Rep_df.rdd.getNumPartitions)

  spark.stop()
}
