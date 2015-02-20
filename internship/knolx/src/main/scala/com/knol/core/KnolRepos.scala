package com.knol.core

trait KnolRepos{
  def create(knol: Knolders):Option[Int]
  def update(knol: Knolders, id: Int): Option[Int]
  def delete(id: Int): Option[Int]
  def getKnolder(id: Int): Option[Knolders]
  def getAllKnolders(): Option[Map[Int,Knolders]]
}

case class Knolders(name: String, email:String, contact:String)
