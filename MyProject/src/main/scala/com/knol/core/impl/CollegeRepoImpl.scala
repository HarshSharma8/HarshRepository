package com.knol.core.impl
import com.knol.core._
import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import com.typesafe.config.ConfigFactory
import com.knol.db.connection.DbConnection
import scala.collection.mutable.MutableList

class CollegeRepoImpl extends CollegeRepo with DbConnection {
  
  
  /**implicit var session = Database.forURL(url = "jdbc:postgresql://localhost:5432/slickdemo", 
                                user = "postgres",
                                password = "postgres", 
                                driver = "org.postgresql.Driver").withSession((table.ddl).create)
   * 
   */
  val table = TableQuery[CollegeTable]
  
  def insert(clg: College): Either[String, Int] =
    try {
      var noOfRowsEff = 0
      val con = getConnection().fold(
        result => "Unable to create connection",
        connection => connection.withSession { implicit session =>noOfRowsEff = table.insert(clg)})
      Right(noOfRowsEff)
    } catch {
      case ex: Exception =>
        Left("Unable to insert a record into College")
    }

  def update(college: College, ids: Int): Either[String, Int] = {
    try {
      var noOfRowsEff = 0
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {noOfRowsEff = table.filter(_.id === ids).update(college) }))
      Right(noOfRowsEff)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to update a record into College")
    }
  }

  def getCollege(ids: Int): Either[String, MutableList[College]] = {
    try {
      var collegeRec = MutableList[College]()
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {collegeRec :+ table.filter(_.id === ids).list}))
      Right(collegeRec)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to return a college")
    }
  }

  def getAllColleges(): Either[String, List[College]] = {
    try {
      var collegeRec = List[College]()
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {collegeRec = table.list}))
      Right(collegeRec)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to return the colleges")
    }
  }

  def delete(ids: Int): Either[String, Int] = {
    try {
      var noOfRowsEff = 0
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {noOfRowsEff = table.filter(_.id === ids).delete }))
      Right(noOfRowsEff)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to delete a record from college")
    }
  }
}

