package com.example

import org.apache.spark.sql.SparkSession

object DF_Aggregation extends App {

  val spark = SparkSession
    .builder()
    .master("local[2]")
    .appName("Aggregation")
    .getOrCreate()

  val csv_path = "/Users/nilanjan/Google Drive/My Drive/IT/Data Engineering /Bigdata/Spark/DataSets/order_data.csv"

  val invoiceDf = spark.read
    .option("inferSchema", true)
    .option("header", true)
    .format("csv")
    .load(csv_path)

  invoiceDf.show(50)
  invoiceDf.printSchema()
  invoiceDf.createOrReplaceTempView("Invoice")
  val transformDF = spark.sql("""
       select count(StockCode) as Row_count, sum(Quantity) as Total_Quantity, avg(UnitPrice) as Avg_Price, COUNT(DISTINCT InvoiceNo) AS distinct_Invoice
       from Invoice
      """.stripMargin)

  val groupTransformDF = spark.sql("""
       select Country, InvoiceNo, sum(Quantity) as TotalQuantity, sum(Quantity * UnitPrice) as Value
       from Invoice group by Country, InvoiceNo
      """.stripMargin)
  transformDF.show()
  groupTransformDF.show()

  // Group the data based on Country and Invoice Number, I want total quantity
  // for each group, sum of invoice value.


  spark.stop()
}

// Count(StockCode will only take the not null value)