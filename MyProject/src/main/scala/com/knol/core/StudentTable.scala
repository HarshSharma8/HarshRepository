package com.knol.core

import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape

class StudentTable(tag: Tag) extends Table[Student](tag, "student") {

  implicit val util2sqlDateMapper = MappedColumnType.base[java.util.Date, java.sql.Date](
    { utilDate => new java.sql.Date(utilDate.getTime()) },
    { sqlDate => new java.util.Date(sqlDate.getTime()) })

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)
  def joiningDate = column[Date]("joiningdate")
  def collegeId = column[Int]("college_id", O.NotNull)
  def * = (id, name, joiningDate, collegeId) <> (Student.tupled, Student.unapply)
  def fKey = foreignKey("coll_student_fk", collegeId, TableQuery[CollegeTable])(_.id)
}
