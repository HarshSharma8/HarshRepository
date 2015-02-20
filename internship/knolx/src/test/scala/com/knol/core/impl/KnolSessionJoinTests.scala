package com.knol.core.impl

import org.scalatest.FunSuite
import com.knol.core.Session
import org.slf4j.LoggerFactory
import com.knol.core.SessionRecord
import scala.collection.mutable.MutableList
import com.knol.core.SessionRecord
import org.scalatest.BeforeAndAfter
import com.knol.db.connection.DbConnection

class KnolSessionJoinTests extends FunSuite with BeforeAndAfter with DbConnection{
    before {
        try {
          val connection = getConnection()
          connection match {
            case Some(connection) => {
              val statement = connection.createStatement()
              statement.executeUpdate("use KnolMembers")
              statement.executeUpdate("create table KnolSessions (" +
                "uid int AUTO_INCREMENT," +
                "topic VARCHAR(20) NOT NULL," +
                "date VARCHAR(20) NOT NULL," +
                "id int," +
                "PRIMARY KEY(uid))")
              statement.executeUpdate("create table KnolStaff (" +
                "id int AUTO_INCREMENT," +
                "name VARCHAR(20) NOT NULL," +
                "email VARCHAR(20) NOT NULL," +
                "contact VARCHAR(12) NOT NULL," +
                "PRIMARY KEY(id), UNIQUE(email,contact))")
                
              statement.executeUpdate("insert into KnolStaff(name,email,contact) values('Harsh','harsh@knoldus','9785544407')")
              statement.executeUpdate("insert into KnolStaff(name,email,contact) values('Sanskriti','sans@knoldus','9529133373')")
              statement.executeUpdate("insert into KnolStaff(name,email,contact) values('Srishti','sri@knoldus','8963827289')")
              
              statement.executeUpdate("insert into KnolSessions(topic,date,id) values('Scala Collections','Feb 11 2015',1)")
              statement.executeUpdate("insert into KnolSessions(topic,date,id) values('Pro 3 D','Feb 11 2016',1)")
              statement.executeUpdate("insert into KnolSessions(topic,date,id) values('Mind Tech','Feb 15 2015',2)")
              connection.close()
            }
            case _ => None
          }

        } catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      }
      
      after {
        try {
          val connection = getConnection()
          connection match {
            case Some(connection) => {
              val statement = connection.createStatement()
              statement.executeUpdate("drop table KnolSessions")
              statement.executeUpdate("drop table KnolStaff")
              connection.close()
            }
            case _ => None
          }

        } catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      }
      
      val sessionRedoImpl = new KnolSessionImpl

      /**
       * Tests getSessionsById() method to numerous sessions of a particular member from table KnolSessions
       * Arguments: id as integer to search a record
       */

      test("getSessionsById() from records: ") {
        val result: Option[Map[Int, SessionRecord]] = sessionRedoImpl.getSessionsById(1)
        val logger = LoggerFactory.getLogger(this.getClass)
        logger.info("GET SESSIONS BY ID EXECUTED:  " + result)
        assert(!(result === None))

      }

}
