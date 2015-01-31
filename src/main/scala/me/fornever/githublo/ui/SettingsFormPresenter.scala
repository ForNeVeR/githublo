package me.fornever.githublo.ui

import java.io.{FileOutputStream, File, FileInputStream}
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
                            private val trelloApiKeyField: TextField,
                            private var configuration: Configuration) {

  private val TrelloApiKeyUrl = "https://trello.com/1/appKey/generate"
  var trelloApiKey = new StringProperty()

  trelloApiKeyField.text <==> trelloApiKey

  def getTrelloApiKey(): Unit = {
    hostServices.showDocument(TrelloApiKeyUrl)
  }

  // TODO: Partially (?) block the UI while working with files
  def loadFromFile(): Unit = {
    import FXExecutor.executor
    val dialog = new FileChooser {
      title = "Select File"
    }

    Option(dialog.showOpenDialog(primaryStage)) match {
      case Some(file) => async {
        await(loadConfiguration(file))

        trelloApiKey.value = configuration.trelloApiKey
      }

      case None =>
    }
  }

  def saveToFile(): Unit = {
    import FXExecutor.executor
    val dialog = new FileChooser {
      title = "Select File"
    }

    Option(dialog.showSaveDialog(primaryStage)) match {
      case Some(file) => async {
        configuration = Configuration(trelloApiKey.value)

        await(saveConfiguration(file))
      }

      case None =>
    }
  }

  private def loadConfiguration(file: File) = async {
    managed(new FileInputStream(file)) acquireAndGet { stream =>
      val properties = new Properties()
      properties.load(stream)

      configuration = Configuration.loadFrom(properties)
    }
  }(scala.concurrent.ExecutionContext.Implicits.global)

  private def saveConfiguration(file: File) = async {
    managed(new FileOutputStream(file)) acquireAndGet { stream =>
      configuration.toProperties.store(stream, "")
    }
  }(scala.concurrent.ExecutionContext.Implicits.global)

}