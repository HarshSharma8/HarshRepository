package com.knol.core

import scala.collection.mutable.MutableList

trait CollegeRepo {
  def insert(college: College): Either[String, Int]
  def update(college: College, id: Int): Either[String, Int]
  def getCollege(id: Int): Either[String, MutableList[College]]
  def getAllColleges(): Either[String, List[College]]
  def delete(id: Int): Either[String, Int]
}

case class College(id: Int, name: String, location: String)