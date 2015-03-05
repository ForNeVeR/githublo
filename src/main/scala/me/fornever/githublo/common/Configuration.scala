package me.fornever.githublo.common

import java.util.Properties

case class Configuration(trelloApiKey: String, trelloToken: String) {

  def toProperties = {
    val properties = new Properties()
    properties.setProperty(Configuration.TrelloApiKey, trelloApiKey)
    properties.setProperty(Configuration.TrelloToken, trelloToken)
    properties
  }

}

object Configuration {

  def apply(): Configuration = Configuration("", "")

  def from(properties: Properties) = {
    Configuration(
      properties.getProperty(TrelloApiKey, ""),
      properties.getProperty(TrelloToken, "")
    )
  }

  def from(content: String): Configuration = {
    sys.error("not implemented") // TODO: Load and parse Clojure file.
  }

  private val TrelloApiKey = "trello.apiKey"
  private val TrelloToken = "trello.token"

}
