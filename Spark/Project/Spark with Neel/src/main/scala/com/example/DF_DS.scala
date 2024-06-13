package com.example

import org.apache.spark.sql.SparkSession

object DF_DS extends App {

  case class Person(name:String, age:Int, city:String)

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("DF to DS")
    .getOrCreate()

  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/dataset1"

  // Load the Data
  val df = spark.read
    .format("csv")
    .option("inferSchema", "true")
    .load(csv_path)

  df.show(false)

  // To Transform this to a DataSet, We need to define a case class. Need Import as well.
  import spark.implicits._
  val ds = df.as[Person]
  ds.show()

  // Transform to DataFrame from DataSet
  val df1 = ds.toDF()
  df1.show()

  spark.stop()

}
