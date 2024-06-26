package com.example

import org.apache.spark.SparkContext

object RDD_ReadFile extends App {

  val sc = new SparkContext(master = "local", appName = "Read Text")
  val sourceRDD = sc
      .textFile(path = "/Users/nilanjan/Documents/IT/BigData/Spark/Project/Files/readfile.txt")

  // Print the First Line of the file
  sourceRDD.take(1).foreach(println)

  // Print the entire file
  sourceRDD.foreach(println)


  sc.stop()
}