package com.example

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object Word_Count extends App {

  val spark = SparkSession
    .builder()
    .appName("WordCount")
    .master("local[2]")
    .getOrCreate()

  val sc = new SparkContext //we need to use sparkcontext in order to do rdd ops.

  val rdd = sc.textFile(path = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile.txt")
  rdd.foreach(println)

spark.stop()
}
