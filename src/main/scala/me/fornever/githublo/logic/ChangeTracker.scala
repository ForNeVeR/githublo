package me.fornever.githublo.logic

class ChangeTracker {
  def createCard(cardModel: CardModel)
  def updateCard(cardModel: CardModel)

  def createdCards: Stream[CardModel]
  def updatedCards: Stream[CardModel]
}
