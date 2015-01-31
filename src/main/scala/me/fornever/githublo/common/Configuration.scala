package me.fornever.githublo.common

import java.util.Properties

case class Configuration(trelloApiKey: String) {

  def saveTo(properties: Properties): Unit = {
    properties.setProperty(Configuration.TrelloApiKey, trelloApiKey)
  }

}

object Configuration {

  private val TrelloApiKey = "trello.apiKey"

  def loadFrom(properties: Properties) = {
    Configuration(properties.getProperty(TrelloApiKey, ""))
  }

}
