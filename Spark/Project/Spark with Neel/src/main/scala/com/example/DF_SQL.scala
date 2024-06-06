package com.example

import org.apache.spark.sql.SparkSession

object DF_SQL extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("SQL")
    .getOrCreate()

  val path = "/Users/nilanjan/Documents/IT/BigData/Spark/Project/Files/Flight.csv"

  val df = spark.read
    .format("csv")
    .option("Header", "true")
    .option("inferSchema", "true")
    .load(path)

  df.show()
  df.createOrReplaceTempView("Flight")
  val result = spark.sql(
    """
      select * from Flight where DEST_COUNTRY_NAME = 'United States'

      """)

  result.explain()

  spark.stop()
}

