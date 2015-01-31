package me.fornever.githublo.ui

import javafx.application.{Application, HostServices}
import javafx.scene.Parent
import javafx.stage.Stage

import scala.reflect.runtime.universe._
import scalafx.Includes._
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, ExplicitDependencies, DependenciesByType}

class FXApplication extends Application {

  override def start(primaryStage: Stage): Unit = {
    stage = new scalafx.stage.Stage(primaryStage) {
      title = "Githublo"
      scene = createScene(root)
    }

    primaryStage.show()
  }

  private val Stylesheet = "/css/main.css"

  private var stage: scalafx.stage.Stage = _
  private def root = FXMLView(getClass.getResource("/fxml/SettingsForm.fxml"), new ComplexDependencyResolver(
    new ExplicitDependencies(Map(
      "primaryStage" -> stage
    )),
    new DependenciesByType(Map(
      typeOf[HostServices] -> getHostServices
    ))))

  private def createScene(view: Parent) = {
    val scene = new Scene(view)
    scene.stylesheets.add(Stylesheet)
    scene
  }

}

object FXApplication {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[FXApplication], args: _*)
  }

}