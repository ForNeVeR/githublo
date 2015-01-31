package me.fornever.githublo.ui

import javafx.application.HostServices

import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class SettingsFormPresenter(private val hostServices: HostServices,
                            private val trelloApiKeyField: TextField) {

  private val TrelloApiKeyUrl = "https://trello.com/1/appKey/generate"

  def getTrelloApiKey(): Unit = {
    hostServices.showDocument(TrelloApiKeyUrl)
  }

}