package me.fornever.githublo.trello

import me.fornever.githublo.github.Issue

class Board(name: String) {

  def loadCards(): Stream[Card] = {
    throw new NotImplementedError()
  }

  def updateCards(issues: Stream[Issue], cards: Stream[Card]) {
    throw new NotImplementedError()
  }

}
