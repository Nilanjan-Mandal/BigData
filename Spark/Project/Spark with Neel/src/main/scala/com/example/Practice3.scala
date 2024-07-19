package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructField, StructType}

object Practice3 extends App {

  var spark = SparkSession
    .builder()
    .master("local[1]")
    .appName("Good Morning")
    .getOrCreate()

  val sc = spark.sparkContext
  val path = "/Users/nilanjan/Documents/IT/BigData/Spark/Project/Files/readfile.txt"
  val path2 = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/schematest.txt"
  val lst = List("Happy", "Birthday", "Day", "Nilanjan", 12, 32)

  val rdd = sc.parallelize(lst)
  val rdd1 = rdd.map(x => (x,1)).collect().foreach(println)

  val rdd2 = sc.textFile(path)
  val rdd3 = rdd2.flatMap(x => x.split(" "))
  val rdd4 = sc.textFile(path2)
  val rdd5 = rdd4.flatMap(x => x.split("|")).collect().foreach(println)
  val rdd6 = rdd3.map(x => (x,1))
  val rdd7 = rdd6.reduceByKey((x,y) => (x+y))
  rdd7.collect().foreach(println)


  spark.stop()
}
