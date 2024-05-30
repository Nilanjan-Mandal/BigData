package com.example

import org.apache.hadoop.fs.shell.Truncate
import org.apache.spark.sql.SparkSession

object DF_Read_File extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("read_with_dataframe")
    .getOrCreate()

  // Path
  val path = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile.txt"

  // Read the file into DataFrame

  val df = spark.read
    .text(path)

  df.show(truncate = false)


  spark.stop()
}

// Read the File in complete sentence : Give that option in show.
