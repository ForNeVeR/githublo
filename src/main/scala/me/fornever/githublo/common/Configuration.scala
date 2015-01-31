package me.fornever.githublo.common

import java.util.Properties

case class Configuration(trelloApiKey: String) {

  def toProperties = {
    val properties = new Properties()
    properties.setProperty(Configuration.TrelloApiKey, trelloApiKey)
    properties
  }

}

object Configuration {

  def apply(): Configuration = Configuration("")

  def loadFrom(properties: Properties) = {
    Configuration(properties.getProperty(TrelloApiKey, ""))
  }

  private val TrelloApiKey = "trello.apiKey"

}
