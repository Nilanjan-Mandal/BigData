package com.theory

import org.apache.spark.sql.SparkSession

object sparksession extends App {

  val spark = SparkSession
    .builder()
    .appName("Template for Spark Session")
    .master("loccal[2]")
    .getOrCreate()


  spark.stop()

}
