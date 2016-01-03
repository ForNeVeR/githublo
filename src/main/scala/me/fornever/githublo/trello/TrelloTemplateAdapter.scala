package me.fornever.githublo.trello

import me.fornever.githublo.logic.{ListModel, CardModel}
import org.trello4j.core.TrelloTemplate

import scala.collection.JavaConversions._
import scala.concurrent.Future

class TrelloTemplateAdapter(key: String, token: String) extends ITrelloAdapter {

  val trello = new TrelloTemplate(key, token)

  override def getCards(boardId: String): Future[Seq[CardModel]] = {
    val board = trello.boundBoardOperations(boardId)
    val lists = board.getList().toStream
    val cards = board.getCards().toStream
    val listModels = lists.map(ListModel.from).map(list => (list.id.get, list)).toMap
    val cardModels = cards.map(convertCard(listModels))
    Future.successful(cardModels)
  }

  override def createCard(listId: String, name: String): Future[Unit] = {
    val list = trello.boundListOperations(listId)
    list.createCard(name, "", "", "", "", "", "", "")
    Future.successful(Unit)
  }

  private def convertCard(lists: Map[String, ListModel])(card: org.trello4j.model.Card) = CardModel.from(card, lists)
}
