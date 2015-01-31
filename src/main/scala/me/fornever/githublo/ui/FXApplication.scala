package me.fornever.githublo.ui

import javafx.application.{Application, HostServices}
import javafx.stage.Stage

import scala.reflect.runtime.universe._
import scalafx.Includes._
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView}

class FXApplication extends Application {

  def root = FXMLView(getClass.getResource("/fxml/SettingsForm.fxml"), new DependenciesByType(Map(
    typeOf[HostServices] -> getHostServices
  )))

  override def start(primaryStage: Stage): Unit = {
    val stage = new scalafx.stage.Stage(primaryStage) {
      title = "Githublo"
      scene = {
        val s = new Scene(root)
        s.stylesheets.add("/css/main.css")
        s
      }
    }

    stage.show()
  }
}

object FXApplication {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[FXApplication], args: _*)
  }

}