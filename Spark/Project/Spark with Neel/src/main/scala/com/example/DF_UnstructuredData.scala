package com.example

import org.apache.spark.sql.SparkSession


object DF_UnstructuredData extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Unstructured Data")
    .getOrCreate()

  val sc = spark.sparkContext

  val my_regex = """/(\S+) (\S+)\t(\S+)\,(\S+)""".r
  case class Orders(order_id:Int, customer_id:Int, order_status:String)

  // Make a Function name Parser
  def parser(line: String) = {
     line match {
       case my_regex(order_id, date, customer_id, order_status) =>
            Orders(order_id.toInt, customer_id.toInt, order_status)
     }
  }

  val lines = sc.textFile("/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/orders_new.csv")
  val rdd = lines.map(parser)

  //Convert to DataSet and Cache the result, so that we won't perform all the steps next time
  import spark.implicits._
  val ds = rdd.toDS()
      .cache()

  ds.printSchema()
  ds.select("order_id").show(false)

}
