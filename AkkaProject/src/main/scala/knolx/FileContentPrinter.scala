package knolx
import scala.io.Source
import akka.actor.ActorSystem
import akka.actor.Props

case class Sentence(sentence: String)

object FileContentPrinter {
  def main(args: Array[String]) = {
    val system = ActorSystem("MySystem")
    val myActor = system.actorOf(Props[MyActor], name = "myactor")
    try {
      val filename = "/home/knoldus/Desktop/TemporaryFiles/textdata"
      for (line <- Source.fromFile(filename).getLines()) {
        myActor ! Sentence(line)
        myActor ! "countWordsInLine"
      }
    } catch {
      case ex: Exception => ex.printStackTrace()
    } finally {
      myActor ! "endOfSentences"
    }

  }
}