package me.fornever.githublo.ui

import java.io.{File, FileInputStream}
import java.util.Properties
import javafx.application.HostServices

import me.fornever.githublo.common.Configuration

import scala.async.Async.{async, await}
import scalafx.beans.property.StringProperty
import scalafx.scene.control.TextField
import scalafx.stage.{FileChooser, Stage}
import scalafxml.core.macros.sfxml
import resource._

@sfxml
class SettingsFormPresenter(primaryStage: Stage,
                            private val hostServices: HostServices,
                            private val trelloApiKeyField: TextField) {

  private val TrelloApiKeyUrl = "https://trello.com/1/appKey/generate"
  var trelloApiKey = new StringProperty()

  trelloApiKeyField.text <== trelloApiKey

  def getTrelloApiKey(): Unit = {
    hostServices.showDocument(TrelloApiKeyUrl)
  }

  def loadFromFile(): Unit = {
    import FXExecutor.executor
    val dialog = new FileChooser {
      title = "Select File"
    }

    Option(dialog.showOpenDialog(primaryStage)) match {
      case Some(file) => async {
        val configuration = await(loadConfiguration(file))
        updateConfigurationData(configuration)
      }

      case None =>
    }
  }

  def saveToFile(): Unit = {

  }

  private def loadConfiguration(file: File) = async {
    managed(new FileInputStream(file)) acquireAndGet { stream =>
      val properties = new Properties()
      properties.load(stream)

      Configuration.loadFrom(properties)
    }
  }(scala.concurrent.ExecutionContext.Implicits.global)

  private def updateConfigurationData(configuration: Configuration): Unit = {
    trelloApiKey.value = configuration.trelloApiKey
  }

}