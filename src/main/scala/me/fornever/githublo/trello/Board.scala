package me.fornever.githublo.trello

import me.fornever.githublo.logic.{ListModel, IssueModel, CardModel}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.collection.JavaConversions._

class Board(boardId: String, listId: String) {

  val titleRegex = """^GH#(\d+).*$""".r
  val maxChangesPerSession = 5

  def updateCards(trello: ITrelloAdapter, issues: Stream[IssueModel], cards: Stream[CardModel]): Int = {
    val cardMap = cardsByGithubId(cards)

    var createdCards = 0
    for (issue <- issues) {
      cardMap.get(issue.id.get) match {
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

  private def cardsByGithubId(cards: Stream[CardModel]): Map[Int, CardModel] = {
    (cards map { card => (card.title, card)} collect { case (titleRegex(number), card) => (number.toInt, card)}).toMap
  }

  private def createCard(trello: ITrelloAdapter, issue: IssueModel) = {
    val name = getCardName(issue)
    trello.createCard(listId, name)
  }

  private def getCardName(issue: IssueModel): String = {
    s"GH#${issue.id}: ${issue.title}"
  }
}
