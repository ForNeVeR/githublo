package me.fornever.githublo

import me.fornever.githublo.github.IssueImporter
import me.fornever.githublo.trello.Board

object Application extends App {

  override def main(args: Array[String]) {
    args match {
      case Array(owner, repo, board) =>
        exportIssues(owner, repo, board)
      case _ =>
        printUsage()
    }
  }

  def exportIssues(owner: String, repo: String, board: String) {
    val github = new IssueImporter(owner, repo)
    val trello = new Board(board)

    val issues = github.loadIssues()
    val cards = trello.loadCards()

    trello.updateCards(issues, cards)
  }

  def printUsage() {
    print("githublo owner repo board")
  }

}
