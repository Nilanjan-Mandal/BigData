package com.theory

import org.apache.spark._
import org.apache.spark.streaming._

object streaming extends App {

  val sc = new SparkContext("local[*]", "wordcount")

  //Create a Spark Streaming Context, It taken 2 input. SparkContext and Batch Interval
  val ssc = new StreamingContext(sc, Seconds(7))
  // Create a Dstream
  val lines = ssc.socketTextStream("localhost",9998)
  val words = lines.flatMap(x => x.split(" "))
  val pairs = words.map(x => (x,1))
  val wordCounts = pairs.reduceByKey((x,y)=> x+y)
  wordCounts.print()
  ssc.start()

  ssc.awaitTermination()

}
