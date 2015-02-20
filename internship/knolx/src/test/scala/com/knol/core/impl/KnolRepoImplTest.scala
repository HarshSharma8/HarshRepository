package com.knol.core.impl

import org.scalatest.FunSuite
import com.knol.core.Knolders
import org.slf4j.LoggerFactory
import org.scalatest.BeforeAndAfter
import com.knol.db.connection.DbConnection

class KnolRepoImplTest extends FunSuite with BeforeAndAfter with DbConnection {
  before {
    try {
      val connection = getConnection()
      connection match {
        case Some(connection) => {
          val statement = connection.createStatement()
          statement.executeUpdate("use KnolMembers")
          statement.executeUpdate("create table KnolStaff (" +
            "id int AUTO_INCREMENT," +
            "name VARCHAR(20) NOT NULL," +
            "email VARCHAR(20) NOT NULL," +
            "contact VARCHAR(12) NOT NULL," +
            "PRIMARY KEY(id), UNIQUE(email,contact))")
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
  val knolRedoImpl = new KnolRepoImpl
  val knol1 = Knolders("Harsh", "harsh@knoldus", "9785544407")
  val knol2 = Knolders("Sanskriti", "sans@knoldus", "9529133373")
  val knol = Knolders("Rashmi", "rashmi@knoldus", "9529133373")

  /**
   * Tests create() method to insert a new record into table KnolStaff
   * arguments: instance of case class Knolders
   */

  test("create() new record: ") {
    val result1 = knolRedoImpl.create(knol1)
    val result2 = knolRedoImpl.create(knol2)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("INSERT EXECUTED: ", result1)
    assert(result1 === Some(1))
  }

  /**
   * Tests update() method to update a record into table KnolStaff
   * arguments: instance of case class Knolders
   */

  test("update() a record: ") {
    val result = knolRedoImpl.update(knol, 1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("UPDATE EXECUTED: ", result)
    assert(result === Some(1))
  }

  /**
   * Tests getKnolder() method to retrieve a record from table KnolStaff
   * arguments: id as integer to search a record
   */

  test("getKnolder() from records: ") {
    val result = knolRedoImpl.getKnolder(1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("GET KNOLDER EXECUTED: ", result)
    assert(!(result === None))
  }

  /**
   * Tests getAllKnolders() method to fetch all knolders from table KnolStaff
   */

  test("getAllKnolders() from records: ") {
    val result: Option[Map[Int, Knolders]] = knolRedoImpl.getAllKnolders()
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("GET ALL KNOLDERS EXECUTED:  " + result)
    assert(!(result === None))
  }

  /**
   * Tests delete() method to delete a record from table KnolStaff
   * arguments: id as integer to search a record
   */

  test("delete() a record: ") {
    val result = knolRedoImpl.delete(1)
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("DELETE KNOLDER EXECUTED: ", result)
    assert(result === Some(1))
  }
}
