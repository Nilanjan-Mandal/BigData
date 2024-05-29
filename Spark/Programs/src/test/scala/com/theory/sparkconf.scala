package com.theory

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object sparkconf extends App {

  val sparkConf = new SparkConf()
  sparkConf.set("spark.app.name", "Template Spark Conf")
  sparkConf.set("spark.master", "local[1]")
  val spark = SparkSession
        .builder()
        .config(sparkConf)
        .getOrCreate()

  spark.stop()

}
