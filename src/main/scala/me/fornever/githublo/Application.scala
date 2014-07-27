package me.fornever.githublo

import me.fornever.githublo.github.IssueImporter
import me.fornever.githublo.trello.Board

object Application extends App {

  override def main(args: Array[String]) {
    args match {
      case Array(owner, repo, board, list, key, token) =>
        exportIssues(owner, repo, board, list, key, token)
      case _ =>
        printUsage()
    }
  }

  def exportIssues(owner: String, repo: String, board: String, list: String, key: String, token: String) {
    val github = new IssueImporter(owner, repo)
    val trello = new Board(board, list, key, token)

    val issues = github.loadIssues()
    val cards = trello.loadCards()

    val result = trello.updateCards(issues, cards)
    println(s"Successfully updated $result cards")
  }

  def printUsage() {
    println("githublo owner repo trelloBoardId trelloListId trelloAPIKey trelloAPIToken")
  }

}
