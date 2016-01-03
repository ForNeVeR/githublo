package me.fornever.githublo.trello

import me.fornever.githublo.logic.CardModel

import scala.concurrent.Future

trait ITrelloAdapter {

  def getCards(boardId: String): Future[Seq[CardModel]]
  def createCard(listId: String, name: String): Future[Unit]
}
