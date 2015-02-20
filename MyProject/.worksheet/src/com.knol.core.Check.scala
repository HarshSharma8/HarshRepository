package com.knol.core

object Check {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 
  println("Welcome to the Scala worksheet");$skip(24); 
	val n= List(1,2,3,4,5);System.out.println("""n  : List[Int] = """ + $show(n ));$skip(11); 
	var sum=0;System.out.println("""sum  : Int = """ + $show(sum ));$skip(29); val res$0 = 
	n.map((i:Int) => sum=sum+i);System.out.println("""res0: List[Unit] = """ + $show(res$0));$skip(5); val res$1 = 
	sum;System.out.println("""res1: Int = """ + $show(res$1))}
}
