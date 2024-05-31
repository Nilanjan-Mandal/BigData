package com.theory

import org.apache.spark.sql.SparkSession


object structured_streaming extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("structured wordcount")
    .getOrCreate()

    //1. Read from the Stream
    val linesDf = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", "9998")
      .load()

    linesDf.printSchema()

    //2. Process
    val wordsdDf = linesDf.selectExpr("explode(split(value,' ')) as word")
    val countsDf = wordsdDf.groupBy("word").count()

    //3. Write to the Slink

    val wordCountQuery = countsDf.writeStream
      .format("console")
      .outputMode("complete")
      .option("checkpointLocation", "checkpoint-location1")
      .start()

    wordCountQuery.awaitTermination()
}
