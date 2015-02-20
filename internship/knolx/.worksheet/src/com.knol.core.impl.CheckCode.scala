package com.knol.core.impl

object CheckCode {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(92); 
  println("Welcome to the World of Khandals");$skip(456); 
    //def matchTest(x: Int): String =
   /* val x = 2
    val result=x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
    }
    println(result)
   */
   
   def matchTest(x: Any): Any = x match {
			case 1 => "one"
			case "two" => 2
			case y: Float => match {
											case 10.5 => "Case 10.5 executed"
											case 10.6 => "Case 10.6 executed"
											case 10.7 => "Case 10.7 executed"
									}
								
											
			};System.out.println("""matchTest: (x: Any)Any""");$skip(26); 
			println(matchTest(15))}
}
