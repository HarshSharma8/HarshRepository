package knolx

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

class WordsCountActor extends Actor {
  var totalWords = 0
  val log = Logging(context.system, this)

  def receive = {
    case ("wordsInSentence", words: Int) ⇒ {
      totalWords = totalWords + words
    }

    case "showResult" ⇒ {
      log.info("Total words in file are: " + totalWords)
    }

    case _ ⇒ log.info("Problem in recieving wordsCount")
  }
}