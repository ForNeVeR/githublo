package me.fornever.githublo.trello

import org.trello4j.core.TrelloTemplate

import scala.collection.JavaConversions._
import scala.concurrent.Future

class TrelloTemplateAdapter(key: String, token: String) extends ITrelloAdapter {

  val trello = new TrelloTemplate(key, token)

  override def getCards(boardId: String): Future[Seq[Card]] = {
    val board = trello.boundBoardOperations(boardId)
    val cards = board.getCards()
    Future.successful(cards.map(convertCard))
  }

  override def createCard(listId: String, name: String): Future[Unit] = {
    val list = trello.boundListOperations(listId)
    list.createCard(name, "", "", "", "", "", "", "")
    Future.successful(Unit)
  }

  private def convertCard(card: org.trello4j.model.Card) = Card(card.getName)
}
