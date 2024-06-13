package com.example

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
object DF_Joins extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Joins")
    .getOrCreate()


  spark.stop()

}
