package me.fornever.githublo

import me.fornever.githublo.github.IssueImporter
import me.fornever.githublo.trello.Board

object Application extends App {

  override def main(args: Array[String]) {
    args match {
      case Array(owner, repo, board, key) =>
        exportIssues(owner, repo, board, key)
      case _ =>
        printUsage()
    }
  }

  def exportIssues(owner: String, repo: String, board: String, key: String) {
    val github = new IssueImporter(owner, repo)
    val trello = new Board(board, key)

    val issues = github.loadIssues()
    val cards = trello.loadCards()

    trello.updateCards(issues, cards)
  }

  def printUsage() {
    println("githublo owner repo trelloBoardId trelloAPIKey")
  }

}
