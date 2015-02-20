package com.knol.core

trait SessionRepo {

  def create(session: Session): Option[Int]
  def update(session: Session, id: Int): Option[Int]
  def delete(id: Int): Option[Int]
  def getSession(id: Int): Option[Session]
  def getAllSessions(): Option[Map[Int,Session]]
}

case class Session(topic: String, date: String, id: Int)
