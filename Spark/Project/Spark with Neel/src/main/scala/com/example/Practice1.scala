package com.example

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession


object Practice1 extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Practice")
    .getOrCreate()

  val sc = spark.sparkContext

  // RDD creation from a External File
  val path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/orders.csv"
  val rddEx = sc.textFile(path)

  //rddEx.collect().foreach(println)
  rddEx.top(5).foreach(println)

  // RDD creation from a Internal data.
  val list = List(1,2,4,6,"Salman")
  val rddIn = sc.parallelize(list)
  rddIn.collect().foreach(println)

  // Create a Empty RDD
  val rddEmp = sc.emptyRDD
  println("An empty RDD has been created")
  rddEmp.collect()

  // Load the file to a DataFrame
  val df = spark.read
    .format("CSV")
    .option("inferSchema", "true")
    .option("Permissive", "True")
    .option("Header", "True")
    .load(path)

  println("Let's see the dataframe")
  df.show()

  // Convert the External rdd to a dataframe
  import spark.implicits._
  val df2 = rddEx.toDF()
  println("Let's RDD to dataframe")
  df2.take(10).foreach(println)


  spark.stop()
}
