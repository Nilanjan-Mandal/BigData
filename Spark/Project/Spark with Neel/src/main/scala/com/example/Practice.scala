package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types._

object Practice extends App {

  // Create a Spark Session
  val spark = SparkSession
    .builder()
    .master("local[1]")
    .appName("Interview")
    .getOrCreate()

  // Create a SparkContext
  val sc = spark.sparkContext

  // Give the path
  val path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/schematest.txt"

  // Read the file into an RDD
  val rdd = sc.textFile(path)
  rdd.collect().foreach(println)

  // Split the RDD and map it to Rows
  val splitRDD = rdd.map(line => {
    val parts = line.split('|')
    Row(parts(0), parts(1), parts(2), parts(3).toInt)
  })

  // Define custom schema
  val schema = StructType(Seq(
    StructField("id", StringType, true),
    StructField("gender", StringType, true),
    StructField("language", StringType, true),
    StructField("score", IntegerType, true)
  ))

  // Convert the RDD to a DataFrame
  val df = spark.createDataFrame(splitRDD, schema)
  df.show()

  // Stop the Spark Session
  spark.stop()
}
