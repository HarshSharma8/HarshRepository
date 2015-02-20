package com.knol.core.impl
import com.knol.core.Student
import com.knol.core.StudentRepo
import com.knol.db.connection.DbConnection
import com.knol.core.StudentTable
import scala.slick.lifted.TableQuery
import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import com.typesafe.config.ConfigFactory
import com.knol.db.connection.DbConnection
import scala.collection.mutable.MutableList

class StudentRepoImpl extends DbConnection with StudentRepo {
  val table=TableQuery[StudentTable]
  def insert(student: Student): Either[String, Int] = {
    try {
      var noOfRowsEff = 0
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {noOfRowsEff = table.insert(student)}))
      Right(noOfRowsEff)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to insert a record into Student")
    }
  }

  def update(student: Student, ids: Int): Either[String, Int] = {
    try{
      var noOfRowsEff = 0
      val con = getConnection().fold(
          message => println(message),
          connection => connection.withSession(implicit session => {noOfRowsEff = table.filter(_.id===ids).update(student)} ))
          Right(noOfRowsEff)
    }catch{
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to update a record into Student")
    }
  }
  
  def getStudent(ids: Int): Either[String, MutableList[Student]] = {
    try{
      var studentRec= MutableList[Student]()
      val con = getConnection().fold(
          message => println(message),
          connection => connection.withSession(implicit session => {studentRec :+ table.filter(_.id === ids).list}))
            Right(studentRec)
    }catch{
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to return a student")
    }
  }
  
  def getAllStudents(): Either[String, List[Student]] = {
    try{
      var studentRec= List[Student]()
      val con = getConnection().fold(
          message => println(message),
          connection => connection.withSession(implicit session => {studentRec=table.list}))
            Right(studentRec)
    }catch{
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to return the students")
    }
  }
  
  def delete(ids: Int): Either[String, Int] = {
    try {
      var noOfRowsEff = 0
      val con = getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {noOfRowsEff = table.filter(_.id === ids).delete}))
        Right(noOfRowsEff)
    } catch {
      case ex: Exception =>
        //ex.printStackTrace()
        Left("Unable to delete a record from Student")
    }
  }

}