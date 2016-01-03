package me.fornever.githublo.ui

import java.io.{FileOutputStream, File, FileInputStream}
import java.util.Properties
import javafx.application.HostServices

import me.fornever.githublo.common.Configuration

import scala.async.Async.{async, await}
import scala.concurrent.Future
import scalafx.beans.property.StringProperty
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.stage.{FileChooser, Stage}
import scalafxml.core.macros.sfxml
import resource._

@sfxml
class SettingsFormPresenter(primaryStage: Stage,
                            private var configuration: Configuration,
                            private val hostServices: HostServices,
                            private val trelloApiKeyField: TextField,
                            private val trelloTokenField: TextField,
                            private val loadButton: Button,
                            private val saveButton: Button,
                            private val statusLabel: Label) {

  private val TrelloApiKeyUrl = "https://trello.com/1/appKey/generate"

  val trelloApiKey = new StringProperty()
  val trelloToken = new StringProperty()
  val status = new StringProperty()

  trelloApiKeyField.text <==> trelloApiKey
  trelloTokenField.text <==> trelloToken
  statusLabel.text <== status

  val controls = List(loadButton, saveButton)

  def getTrelloApiKey(): Unit = {
    hostServices.showDocument(TrelloApiKeyUrl)
  }

  def getTrelloToken(): Unit = {
    val apiKey = trelloApiKey.value
    val url = s"https://trello.com/1/authorize?key=$apiKey&name=Githublo&expiration=never&response_type=token&scope=read,write"
    hostServices.showDocument(url)
  }

  def blockUi(message: String)(action: Future[Unit]) = async {
    status.value = message
    controls.foreach(_.disable = true)

    await(action)

    controls.foreach(_.disable = false)
    status.value = ""
  }(FXExecutor.executor)

  def loadFromFile(): Unit = {
    import FXExecutor.executor
    val dialog = new FileChooser {
      title = "Select File"
    }

    Option(dialog.showOpenDialog(primaryStage)) match {
      case Some(file) => blockUi("Loading data...")(async {
        await(loadConfiguration(file))

        trelloApiKey.value = configuration.trelloApiKey
      })

      case None =>
    }
  }

  def saveToFile(): Unit = {
    import FXExecutor.executor
    val dialog = new FileChooser {
      title = "Select File"
    }

    Option(dialog.showSaveDialog(primaryStage)) match {
      case Some(file) => blockUi("Saving data...")(async {
        configuration = Configuration(trelloApiKey.value, trelloToken.value)

        await(saveConfiguration(file))
      })

      case None =>
    }
  }

  private def loadConfiguration(file: File) = async {
    managed(new FileInputStream(file)) acquireAndGet { stream =>
      val properties = new Properties()
      properties.load(stream)

      configuration = Configuration.from(properties)
    }
  }(scala.concurrent.ExecutionContext.Implicits.global)

  private def saveConfiguration(file: File) = async {
    managed(new FileOutputStream(file)) acquireAndGet { stream =>
      configuration.toProperties.store(stream, "")
    }
  }(scala.concurrent.ExecutionContext.Implicits.global)

}