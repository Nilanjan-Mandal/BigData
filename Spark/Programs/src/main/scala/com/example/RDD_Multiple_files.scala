package com.example

import org.apache.spark.sql.SparkSession

object RDD_Multiple_files extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Multiple Files")
    .getOrCreate()

  val sc = spark.sparkContext

  val path = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile.txt,/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile1.txt"
  
  val rdd = sc.textFile(path)
  rdd.collect().foreach(println)

  spark.stop()
}
