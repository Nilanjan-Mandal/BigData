package com.example

import org.apache.spark.sql.SparkSession

object RDD_Create extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Create_RDD")
    .getOrCreate()

  val sc = spark.sparkContext

  val data = List(1,2,3,4,5,6)

  val rdd = sc
      .parallelize(data)
  rdd.collect().foreach(println)


  spark.stop()
}


// What is parallelize in spark?
// parallelize is a function in SparkContext that is used to create a RDD from
// a local Scala/Python collection.