package com.knol.core

import scala.collection.mutable.MutableList

trait KnolSessionRepo {
  //def getSessionsById(id: Int): Option[MutableList[Session]]
  def getSessionsById(id: Int): Option[Map[Int,SessionRecord]]
}

case class SessionRecord(name: String, email: String, contact: String, topic: String, date: String)
