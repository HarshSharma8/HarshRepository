package com.knol.db.connection

import com.typesafe.config.ConfigFactory
import java.sql.DriverManager

trait DbConnection {
  val config = ConfigFactory.load()
  val url = config.getString("db.url")
  val user = config.getString("db.user")
  val password = config.getString("db.password")

  
  /**
   * This method creates and returns the connection object
   * to the test file "KnolRepoImplTest.scala"
   */
      
      def getConnection(): Option[java.sql.Connection] =
        {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val connection = DriverManager.getConnection(url, user, password)
            Some (connection)
            }
        catch {
            case ex: Exception =>{
              ex.printStackTrace()
            None  
            }
            
            }
        }
}
