package me.fornever.githublo.ui

import javafx.application.{Application, HostServices}
import javafx.stage.Stage

import scala.reflect.runtime.universe._
import scalafx.Includes._
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView}

class FXApplication extends Application {

  def root = FXMLView(getClass.getResource("settings.fxml"), new DependenciesByType(Map(
    typeOf[HostServices] -> getHostServices
  )))

  override def start(primaryStage: Stage): Unit = {
    val stage = new scalafx.stage.Stage(primaryStage) {
      title = "Githublo"
      scene = new Scene(root)
    }

    stage.show()
  }
}

object FXApplication {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[FXApplication], args: _*)
  }

}