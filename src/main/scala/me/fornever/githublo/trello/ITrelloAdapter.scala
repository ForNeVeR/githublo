package me.fornever.githublo.trello

import scala.concurrent.Future

trait ITrelloAdapter {

  def getCards(boardId: String): Future[Seq[Card]]
  def createCard(listId: String, name: String): Future[Unit]
}
