package me.fornever.githublo.trello

import me.fornever.githublo.github.Issue

import scala.concurrent.Await
import scala.concurrent.duration._

class Board(boardId: String, listId: String) {

  val titleRegex = """^GH#(\d+).*$""".r
  val maxChangesPerSession = 5

  def updateCards(trello: ITrelloAdapter, issues: Stream[Issue], cards: Stream[Card]): Int = {
    val cardMap = cardsByGithubId(cards)

    var createdCards = 0
    for (issue <- issues) {
      cardMap.get(issue.id) match {
        case Some(card) => // TODO: Update card based on github status
        case None =>
          val create = trello.createCard(listId, getCardName(issue))
          Await.result(create, 1.minute)

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

  private def getCardName(issue: Issue): String = {
    s"GH#${issue.id}: ${issue.name}"
  }
}
