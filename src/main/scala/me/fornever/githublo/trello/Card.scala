package me.fornever.githublo.trello

case class Card(title: String)

object Card {

  def from(card: org.trello4j.model.Card): Card = {
    Card(card.getName)
  }

}
