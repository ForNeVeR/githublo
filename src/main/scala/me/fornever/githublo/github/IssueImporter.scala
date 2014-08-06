package me.fornever.githublo.github

import me.fornever.githublo.logic.IssueModel

import scala.collection.JavaConversions._
import org.eclipse.egit.github.core.service.IssueService

class IssueImporter(owner: String, repo: String) {

  def loadIssues(): Stream[IssueModel] = {
    val service = new IssueService()
    val issues = service.getIssues(owner, repo, null)
    Stream(issues: _*).map(IssueModel.from)
  }

}
