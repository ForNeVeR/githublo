package me.fornever.githublo.github

import scala.collection.JavaConversions._
import org.eclipse.egit.github.core.service.IssueService

class IssueImporter(owner: String, repo: String) {

  def loadIssues(): Stream[Issue] = {
    val service = new IssueService()
    val issues = service.getIssues(owner, repo, null)
    Stream(issues: _*).map(Issue.from)
  }

}
