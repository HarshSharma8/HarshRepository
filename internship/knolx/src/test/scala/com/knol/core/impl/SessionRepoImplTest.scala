package com.knol.core.impl

import org.scalatest.FunSuite
import com.knol.core.Session
import org.slf4j.LoggerFactory
import com.knol.core.SessionRecord
import scala.collection.mutable.MutableList
import com.knol.core.SessionRecord
import org.scalatest.BeforeAndAfter
import com.knol.db.connection.DbConnection

class SessionRepoImplTest extends FunSuite with BeforeAndAfter with DbConnection{
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
  val session = Session("Angular", "Feb 8 2015", 1)
  val session1 = Session("Java", "Feb 9 2015", 1)
  val session2 = Session("Scala", "Feb 10 2015", 2)
  val session3 = Session("Play", "Feb 11 2015", 2)

  /**
   * Tests create() method to insert a new record into table KnolSessions
   * Arguments: instance of case class Session
   */

  test("create() new record: ") {
    val result = sessionRedoImpl.create(session)
    val result2 = sessionRedoImpl.create(session1)
    val result3 = sessionRedoImpl.create(session2)
    val result4 = sessionRedoImpl.create(session3)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("INSERT SESSION EXECUTED: ", result)
    assert(result === Some(1))
  }

  /**
   * Tests update() method to update a record into table KnolSessions
   * Arguments: instance of case class Session
   */

  test("update() a record: ") {
    val result = sessionRedoImpl.update(session, 1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("UPDATE SESSION EXECUTED: ", result)
    assert(result === Some(1))
  }

  /**
   * Tests getSession() method to retrieve a record from table KnolSessions
   * Arguments: id as integer to search a record
   */

  test("getSession() from records: ") {
    val result = sessionRedoImpl.getSession(1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("GET SESSION EXECUTED: ", result)
    assert(!(result === None))
  }

  /**
   * Tests getAllSessions() method to fetch all sessions from table KnolSessions
   */

  test("getAllSessions() from records: ") {
    val result: Option[Map[Int, Session]] = sessionRedoImpl.getAllSessions()
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("GET ALL SESSIONS EXECUTED:  " + result)
    assert(!(result === None))
  }

  /**
   * Tests delete() method to delete a record from table KnolSessions
   * Arguments: id as integer to search a record
   */

  test("delete() a record: ") {
    val result = sessionRedoImpl.delete(1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("DELETE SESSION EXECUTED: ", result)
    assert(result === Some(1))
  }
  
}
