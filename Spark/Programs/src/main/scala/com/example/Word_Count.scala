package com.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Word_Count extends App {


  val spark = SparkSession
    .builder()
    .appName("WordCount")
    .master("local")
    .getOrCreate()

  val sc = spark.sparkContext

  val rdd = sc.textFile(path = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile.txt")
  rdd.foreach(println)

}


// 1. We need to use sparkcontext to work on rdd.
// 2. Type foreach(println) manually. 