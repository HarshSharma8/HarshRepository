package com.knol.db.connection

import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import com.typesafe.config.ConfigFactory

trait DbConnection {
  
  /**
   * This method creates and returns the connection object
   * to the test file "CollegeTest.scala"
   */

        def getConnection(): Either[String, Database] =
          try {
            val database = Database.forURL(url = "jdbc:postgresql://localhost:5432/slickdemo", user = "postgres",
    password = "postgres", driver = "org.postgresql.Driver")
              Right(database)
          } catch {
            case ex: Exception =>
              Left("Unable to create a connection")
          }
}

/*
 * 
    Postgres Installation:
    
    1. sudo  apt-get install postgresql-9.4
    2. login => sudo -u postgres  psql postgres
    3 . \d  for show table
    4. \c  sbname
 * 
 */

