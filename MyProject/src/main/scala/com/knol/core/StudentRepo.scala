package com.knol.core

import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import scala.collection.mutable.MutableList

trait StudentRepo {
  def insert(student: Student): Either[String, Int]
  def update(student: Student, id: Int): Either[String, Int]
  def getStudent(id: Int): Either[String, MutableList[Student]]
  def getAllStudents(): Either[String, List[Student]]
  def delete(id: Int): Either[String, Int]
}

case class Student(id: Int, name: String, doj: Date, college_id: Int) 