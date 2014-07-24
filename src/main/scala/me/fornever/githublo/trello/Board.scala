package me.fornever.githublo.trello

import scala.collection.JavaConversions._
import me.fornever.githublo.github.Issue
import org.trello4j.{Trello, TrelloImpl}

class Board(boardId: String, key: String) {

  def loadCards(): Stream[Card] = {
    val trello: Trello = new TrelloImpl(key)
    val cards = trello.getCardsByBoard(boardId)
    Stream(cards: _*).map(Card.from)
  }

  def updateCards(issues: Stream[Issue], cards: Stream[Card]) {
    throw new NotImplementedError()
  }

}
