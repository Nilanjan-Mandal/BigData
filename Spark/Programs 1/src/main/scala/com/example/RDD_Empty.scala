package com.example

import org.apache.spark.sql.SparkSession

object RDD_Empty extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Empty")
    .getOrCreate()


  val sc = spark.sparkContext

  val rdd = sc.emptyRDD[String]

    rdd.collect().foreach(println)

spark.stop()
}
