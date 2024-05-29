package com.example

import org.apache.spark.SparkContext

object Read_File extends App {

  val sc = new SparkContext(master = "local", appName = "Read Text")
  val sourceRDD = sc.textFile(path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/scrtipts/readfile.txt")

  // Print the First Line of the file
  sourceRDD.take(1).foreach(println)
  // Print the entire file
  sourceRDD.foreach(println)

}
