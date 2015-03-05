package me.fornever.githublo.test

import org.scalatest._
import me.fornever.githublo.common.Configuration

class ConfigurationSpec extends FlatSpec {

  "Configuration" should "be succesfully loaded from string" in {
    val c = Configuration.from(
      """
        |(defn process-trello-task
        |  [task]
        |  nil)
      """.stripMargin)
    assert(c !== null)
  }

}
