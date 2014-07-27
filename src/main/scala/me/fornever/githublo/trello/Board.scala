package me.fornever.githublo.trello

import java.util

import scala.collection.JavaConversions._
import me.fornever.githublo.github.Issue
import org.trello4j.{Trello, TrelloImpl}

class Board(boardId: String, listId: String, key: String, token: String) {

  val titleRegex = """^GH#(\d+).*$""".r
  val maxChangesPerSession = 5

  def loadCards(): Stream[Card] = {
    val trello: Trello = new TrelloImpl(key, token)
    val cards = trello.getCardsByBoard(boardId)
    Stream(cards: _*).map(Card.from)
  }

  def updateCards(issues: Stream[Issue], cards: Stream[Card]): Int = {
    val cardMap = cardsByGithubId(cards)

    var createdCards = 0
    for (issue <- issues) {
      cardMap.get(issue.id) match {
        case Some(card) => // TODO: Update card based on github status
        case None =>
          createCard(issue)
          createdCards += 1
          if (createdCards >= maxChangesPerSession) {
            return createdCards
          }
      }
    }

    createdCards
  }

  private def cardsByGithubId(cards: Stream[Card]): Map[Int, Card] = {
    (cards map { card => (card.title, card)} collect { case (titleRegex(number), card) => (number.toInt, card)}).toMap
  }

  private def createCard(issue: Issue) {
    val trello: Trello = new TrelloImpl(key, token)
    val parameters = new util.HashMap[String, Object]()
    parameters.put("due", "")
    trello.createCard(listId, getCardName(issue), parameters)
  }

  private def getCardName(issue: Issue): String = {
    s"GH#${issue.id}: ${issue.name}"
  }

}
