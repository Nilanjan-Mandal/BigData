package com.example

import org.apache.hadoop.fs.shell.Truncate
import org.apache.spark.sql.SparkSession

object DF_ReadFile extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("read_with_dataframe")
    .getOrCreate()

  // Path
  val csvpath = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/Performance.csv"
  val textpath = "/Users/nilanjan/Documents/IT/Bigdata/Spark/folder/readfile1.txt"

  // Read the file into DataFrame

  val dfcsv = spark.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load(csvpath)

  val dftxt = spark.read
    .format("text")
    .load(textpath)

  dfcsv.show()
  dftxt.show()


  spark.stop()
}

// Read the File in complete sentence : Give that option in show. df.show(truncate = false)
