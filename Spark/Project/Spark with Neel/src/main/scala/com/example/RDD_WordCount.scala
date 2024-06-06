package com.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object RDD_WordCount extends App {


  val spark = SparkSession
    .builder()
    .appName("WordCount")
    .master("local")
    .getOrCreate()

  val sc = spark.sparkContext

  val rdd = sc.textFile(path = "/Users/nilanjan/Documents/IT/BigData/Spark/Project/Files/readfile.txt")
  //rdd.foreach(println)
  val rdd1 = rdd.flatMap(x => x.split(" "))  // It will split the data based on the space
  val rdd2 = rdd1.map(x => (x,1))
  val rdd3 = rdd2.reduceByKey((x,y) => x+y)
  rdd3.foreach(println)

}


// 1. We need to use sparkcontext to work on rdd.
// 2. Type foreach(println) manually.