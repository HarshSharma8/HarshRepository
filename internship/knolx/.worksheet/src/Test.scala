object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(374); 
   def main(args: Array[String]) {
      val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
      
      println("show(capitals.get( \"Japan\")) : " +
                                          show(capitals.get( "Pak")) )
      println("show(capitals.get( \"India\")) : " +
                                          show(capitals.get( "India")) )
   };System.out.println("""main: (args: Array[String])Unit""");$skip(99); 
   
   def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
   };System.out.println("""show: (x: Option[String])String""")}
}
