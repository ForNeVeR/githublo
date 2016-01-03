package me.fornever.githublo.logic

class ChangeTracker {
  private var createdCardList = List[CardModel]()
  private var updatedCardList = List[CardModel]()
  private var count = 0

  def createCard(cardModel: CardModel) {
    createdCardList +:= cardModel
    count += 1
  }

  def updateCard(cardModel: CardModel) {
    updatedCardList +:= cardModel
    count += 1
  }

  def createdCards: Stream[CardModel] = createdCardList.toStream
  def updatedCards: Stream[CardModel] = updatedCardList.toStream
}
