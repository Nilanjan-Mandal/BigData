package com.example

import org.apache.spark.SparkContext

object RDD_Read_file extends App {

  val sc = new SparkContext(master = "local", appName = "Read Text")
  val sourceRDD = sc
      .textFile(path = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile.txt")

  // Print the First Line of the file
  sourceRDD.take(1).foreach(println)

  // Print the entire file
  sourceRDD.foreach(println)


  sc.stop()
}