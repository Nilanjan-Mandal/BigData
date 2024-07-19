package com.example

import org.apache.spark.sql.SparkSession

object DF_Join extends App {

  val spark = SparkSession.builder
    .appName("DataFrame Join Example")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  // Sample data for DataFrame 1
  val data1 = Seq(
    (1, "Alice", 28),
    (2, "Bob", 25),
    (3, "Cathy", 32)
  )

  val df1 = data1.toDF("id", "name", "age")

  // Sample data for DataFrame 2
  val data2 = Seq(
    (1, "New York"),
    (2, "Los Angeles"),
    (4, "Chicago")
  )

  val df2 = data2.toDF("id", "city")

  // Perform the join
  val joinedDF = df1.join(df2, df1("id") === df2("id"), "inner")

  // Show the result
  joinedDF.show()

  df1.createOrReplaceTempView("Sample1")
  df2.createOrReplaceTempView("Sample2")

  val finaldf = spark.sql(
    """
       select *
       from Sample1 as s1
       inner join Sample2 as s2
       on s1.id = s2.id

      """.stripMargin)

  finaldf.show()

  spark.stop()
}