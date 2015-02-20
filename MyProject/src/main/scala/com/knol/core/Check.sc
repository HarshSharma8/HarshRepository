package com.knol.core

object Check {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	val n= List(1,2,3,4,5)                    //> n  : List[Int] = List(1, 2, 3, 4, 5)
	var sum=0                                 //> sum  : Int = 0
	n.map((i:Int) => sum=sum+i)               //> res0: List[Unit] = List((), (), (), (), ())
	sum                                       //> res1: Int = 15
}