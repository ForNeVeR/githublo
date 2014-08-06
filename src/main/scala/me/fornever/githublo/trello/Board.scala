package me.fornever.githublo.trello

import me.fornever.githublo.logic.{ListModel, IssueModel, CardModel}

import scala.collection.JavaConversions._
import org.trello4j.{Trello, TrelloImpl}

class Board(boardId: String, listId: String, key: String, token: String) {

  val titleRegex = """^GH#(\d+).*$""".r
  val maxChangesPerSession = 5

  def loadCards(): Stream[CardModel] = {
    val trello: Trello = new TrelloImpl(key, token)
    val lists = trello.getListByBoard(boardId).toStream.map(ListModel.from).map(list => (list.id.get, list)).toMap
    val cards = trello.getCardsByBoard(boardId).toStream.map(CardModel.from(_, lists))

    cards
  }

  def updateCards(issues: Stream[IssueModel], cards: Stream[CardModel]): Int = {
    val cardMap = cardsByGithubId(cards)

    var createdCards = 0
    for (issue <- issues) {
      cardMap.get(issue.id.get) match {
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

  private def cardsByGithubId(cards: Stream[CardModel]): Map[Int, CardModel] = {
    (cards map { card => (card.title, card)} collect { case (titleRegex(number), card) => (number.toInt, card)}).toMap
  }

  private def createCard(issue: IssueModel) {
    val trello: Trello = new TrelloImpl(key, token)
    val parameters = new java.util.HashMap[String, Object]()
    parameters.put("due", "")
    trello.createCard(listId, getCardName(issue), parameters)
  }

  private def getCardName(issue: IssueModel): String = {
    s"GH#${issue.id}: ${issue.title}"
  }

}
