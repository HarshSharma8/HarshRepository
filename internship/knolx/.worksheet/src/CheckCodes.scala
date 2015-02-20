object CheckCodes {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(63); 
  println("Welcome to the Scala worksheet");$skip(18); 
	val in = "Harsh";System.out.println("""in  : String = """ + $show(in ));$skip(126); 
	 val result: Either[String,Int] = try {
	     Right(in.toInt)
	   } catch {
	     case e: Exception =>
	       Left("h")
	 };System.out.println("""result  : Either[String,Int] = """ + $show(result ));$skip(127); 
		
	 
		val ans=result.fold( a => println("String output: "+a),
												 s => println("Integer output: "+s)
												 );System.out.println("""ans  : Unit = """ + $show(ans ));$skip(192); 


		println( result match {
	   case Right(x) => "You passed me the Int: " + x + ", which I will increment. " + x + " + 1 = " + (x+1)
	   case Left(x) => "You passed me the String: " + x
	 })}
		
}
