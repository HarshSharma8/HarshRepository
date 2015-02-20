package com.knol.core.impl

import org.scalatest.FunSuite
import org.slf4j.LoggerFactory
import scala.collection.mutable.MutableList
import com.knol.core.College
import org.scalatest.FunSuite
import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import com.knol.core.CollegeTable
import org.scalatest.BeforeAndAfter
import com.knol.db.connection.DbConnection

class CollegeTest  extends FunSuite with BeforeAndAfter with DbConnection{
  val table=TableQuery[CollegeTable]
  val collegeRepoImpl = new CollegeRepoImpl
  val college1 = College(0, "St Willfred", "Mansarovar")
  val college2 = College(0, "Poornima", "Sitapura")
  
  before{
    try {
      getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {table.ddl.create;
                                                                  table.insert(college1);
                                                                  table.insert(college2);}))
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        println("Unable to create table College in test")
    }
  }
  
  after{
    try {
      getConnection().fold(
        message => println(message),
        connection => connection.withSession(implicit session => {table.ddl.drop}))
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        println("Unable to drop table College in test")
    }
  }
  
  val college3 = College(0, "ISIM", "Jaipur")
  
  test("insert() of collegeRepoImpl") {  
    val result = collegeRepoImpl.insert(college3).fold(
      messageError => println("INSERT FAILED - " + messageError),
      value => { assert(value === 1); println("The Insert's been successfully done " + value) })
  }

  test("update() of collegeRepoImpl") {
    val college = College(0, "BMIT", "New Delhi")
    val result = collegeRepoImpl.update(college,1).fold(
      messageError => println("UPDATE FAILED - " + messageError),
      value => { assert(value === 1); println("The Update's been successfully done " + value) })
  }

  test("getCollege() of collegeRepoImpl") {
    val result = collegeRepoImpl.getCollege(1).fold(
      messageError => println("GET COLLEGE FAILED - " + messageError),
      value => { assert(!(value === null)); println("The GetCollege's been successfully done " + value) })
  }

  test("getAllColleges() of collegeRepoImpl") {
    val result = collegeRepoImpl.getAllColleges().fold(
      messageError => println("GET ALL COLLEGES FAILED - " + messageError),
      value => { assert(!(value === null)); println("The GetAllCollege's been successfully done " + value) })
  }
  
  test("delete() of collegeRepoImpl") {
    val result = collegeRepoImpl.delete(1).fold(
      messageError => println("DELETE COLLEGE FAILED - " + messageError),
      value => { assert(value === 1); println("The DeleteCollege's been successfully done " + value) })
  }
}