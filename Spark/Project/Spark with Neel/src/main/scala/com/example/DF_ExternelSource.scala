package com.example

import org.apache.spark.sql.SparkSession

import java.util.Properties

object DF_ExternelSource extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("MySQL_Source")
    .getOrCreate()

  val connect = "jdbc:mysql://localhost:3306/trendytech"
  val mysql_props = new java.util.Properties
  mysql_props.setProperty("user", "root")
  mysql_props.setProperty("password", "$!Nilu@981")

  val emp_df = spark.read.jdbc(connect, "employee", mysql_props)
  emp_df.show()

  spark.stop()

}
