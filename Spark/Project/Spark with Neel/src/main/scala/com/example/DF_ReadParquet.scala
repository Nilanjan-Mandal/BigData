package com.example

import org.apache.spark.sql.SparkSession

object DF_ReadParquet extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Parquet")
    .getOrCreate()

}
