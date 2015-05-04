package me.fornever.githublo

import me.fornever.githublo.github.IssueImporter
import me.fornever.githublo.trello.{TrelloTemplateAdapter, Board}

import scala.concurrent.Await
import scala.concurrent.duration._

object Application extends App {

  override def main(args: Array[String]) {
    args match {
      case Array(owner, repo, board, list, key, token) =>
        exportIssues(owner, repo, board, list, key, token)
      case _ =>
        printUsage()
    }
  }

  def exportIssues(owner: String, repo: String, boardId: String, listId: String, key: String, token: String) {
    val github = new IssueImporter(owner, repo)
    val trello = new TrelloTemplateAdapter(key, token)
    val board = new Board(boardId, listId)

    val issues = github.loadIssues()
    val cards = Await.result(trello.getCards(boardId), 1.minute)

    val result = board.updateCards(trello, issues, cards.toStream)
    println(s"Successfully updated $result cards")
  }

  def printUsage() {
    println("Command line arguments: github-user github-repository trello-board-id trello-list-id trello-api-key trello-api-token")
  }
}
