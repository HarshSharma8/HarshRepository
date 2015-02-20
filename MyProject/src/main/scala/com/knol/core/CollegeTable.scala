package com.knol.core

import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape

class CollegeTable(tag: Tag) extends Table[College](tag, "college") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name: Column[String] = column[String]("name", O.NotNull)
  def location: Column[String] = column[String]("location", O.NotNull)
  def * = (id, name, location) <> (College.tupled, College.unapply)
}
