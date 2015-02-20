object CheckCodes {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	val in = "Harsh"                          //> in  : String = Harsh
	 val result: Either[String,Int] = try {
	     Right(in.toInt)
	   } catch {
	     case e: Exception =>
	       Left("h")
	 }                                        //> result  : Either[String,Int] = Left(h)
		
	 
		val ans=result.fold( a => println("String output: "+a),
												 s => println("Integer output: "+s)
												 )
                                                  //> String output: h
                                                  //| ans  : Unit = ()


		println( result match {
	   case Right(x) => "You passed me the Int: " + x + ", which I will increment. " + x + " + 1 = " + (x+1)
	   case Left(x) => "You passed me the String: " + x
	 })                                       //> You passed me the String: h
		
}