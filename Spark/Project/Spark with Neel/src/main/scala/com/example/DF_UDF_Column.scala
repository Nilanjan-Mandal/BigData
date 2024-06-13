package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{column, expr, udf}

object DF_UDF_Column extends App {


  // Create a function and register as this is high level API.
  def ageCheck(age: Int) = {

    if (age > 18) "Y" else "N"
  }

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("UDF")
    .getOrCreate()


  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/dataset1"

  // Load the Data
   val df = spark.read
     .format("csv")
     .option("inferSchema", "true")
     .load(csv_path)

  // Give the column Name, Otherwise system will assign a random column name
  val df1 = df.toDF("name", "age", "city")
  df1.printSchema()
  df1.show()

  // Register The function
  val parseAgeFunction = udf(ageCheck(_:Int):String)

  // Call The function
  val df2 = df1.withColumn("Adult",parseAgeFunction(column("age"))).show(false)

  // SQL/STRING Expression UDF



  //Stopping the Spark Session
  spark.stop()

}
