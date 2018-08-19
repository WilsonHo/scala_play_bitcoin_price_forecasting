package bao.ho.forecasting.connectDB

import java.util.Properties
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark SQL data sources example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    import spark.implicits._
    runJdbcDatasetExample(spark)
    spark.stop()
  }

  private def runJdbcDatasetExample(spark: SparkSession): Unit = {
    // $example on:jdbc_dataset$
    // Note: JDBC loading and saving can be achieved via either the load/save or jdbc methods
    // Loading data from a JDBC source
    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", "jdbc:postgresql://localhost:5432/bao.ho")
      .option("dbtable", "currency_infos")
      .option("user", "bao.ho")
      .option("password", "bao.ho")
      .load()
    jdbcDF.show()
    import spark.implicits._

//    jdbcDF.map(row => println(row))

    //    val connectionProperties = new Properties()
    //    connectionProperties.put("user", "username")
    //    connectionProperties.put("password", "password")
    //    val jdbcDF2 = spark.read
    //      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
    //    // Specifying the custom data types of the read schema
    //    connectionProperties.put("customSchema", "id DECIMAL(38, 0), name STRING")
    //    val jdbcDF3 = spark.read
    //      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
    //
    //    // Saving data to a JDBC source
    //    jdbcDF.write
    //      .format("jdbc")
    //      .option("url", "jdbc:postgresql:dbserver")
    //      .option("dbtable", "schema.tablename")
    //      .option("user", "username")
    //      .option("password", "password")
    //      .save()
    //
    //    jdbcDF2.write
    //      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
    //
    //    // Specifying create table column data types on write
    //    jdbcDF.write
    //      .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
    //      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
    //    // $example off:jdbc_dataset$
  }

}
