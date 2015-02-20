package com.knol.core.impl
import org.scalatest.FunSuite
import org.slf4j.LoggerFactory
import scala.collection.mutable.MutableList
import com.knol.core.Student
import org.scalatest.FunSuite
import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import org.scalatest.BeforeAndAfter
import com.knol.db.connection.DbConnection
import com.knol.core.StudentTable

class StudentTest extends FunSuite with BeforeAndAfter with DbConnection{
  val table=TableQuery[StudentTable]
  val studentRepoImpl = new StudentRepoImpl
  val student1 = Student(0, "Sooraj Khandal", new Date(1990, 8, 8), 1)
  val student2 = Student(0, "Ram Khandal", new Date(1985, 12, 2), 1)
  
  before{
    try {
      getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {table.ddl.create;
                                                                  table.insert(student1);
                                                                  table.insert(student2)}))
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        println("Unable to create table Student in test")
    }
  }
  
  after{
    try {
      getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {TableQuery[StudentTable].ddl.drop}))
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        println("Unable to drop table Student in test")
    }
  }
  val student3 = Student(0, "Sanskriti Sharma", new Date(1992, 9, 8), 2)
  
  test("insert() of StudentRepoImpl") {  
    val result = studentRepoImpl.insert(student3).fold(
      messageError => println("INSERT FAILED - " + messageError),
      value => { assert(value === 1); println("The Insert's been successfully done " + value) })
  }

  test("update() of StudentRepoImpl") {
    val student = Student(0, "Sooryansh", new Date(2015, 2, 18), 1)
    val result = studentRepoImpl.update(student,1).fold(
      messageError => println("UPDATE FAILED - " + messageError),
      value => { assert(value === 1); println("The Update's been successfully done " + value) })
  }

  test("getStudent() of StudentRepoImpl") {
    val result = studentRepoImpl.getStudent(1).fold(
      messageError => println("GET student FAILED - " + messageError),
      value => { assert(!(value === null)); println("The GetStudent's been successfully done " + value) })
  }

  test("getAllStudents() of studentRepoImpl") {
    val result = studentRepoImpl.getAllStudents().fold(
      messageError => println("GET ALL studentS FAILED - " + messageError),
      value => { assert(!(value === null)); println("The GetAllStudent's been successfully done " + value) })
  }
  
  test("delete() of studentRepoImpl") {
    val result = studentRepoImpl.delete(1).fold(
      messageError => println("DELETE student FAILED - " + messageError),
      value => { assert(value === 1); println("The DeleteStudent's been successfully done " + value) })
  }
}