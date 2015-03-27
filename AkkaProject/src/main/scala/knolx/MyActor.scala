package knolx
import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging
import akka.actor.ActorSystem

class MyActor extends Actor {
  var line = ""
  val log = Logging(context.system, this)
  val system = ActorSystem("MySystem")
  val wordActor = system.actorOf(Props[WordsCountActor], name = "wordactor")

  def receive = {
    case Sentence(sentence: String) => {
      this.line = sentence
    }

    case "countWordsInLine" ⇒
      val wordsList = (line split ("""\s+""") toList)
      wordActor ! (("wordsInSentence", wordsList.length))

    case "endOfSentences" => {
      wordActor ! "showResult"
    }
    case _ ⇒ log.info("Problem in recieving sentence")
  }
}

