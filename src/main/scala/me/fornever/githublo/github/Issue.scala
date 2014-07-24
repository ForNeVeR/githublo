package me.fornever.githublo.github

case class Issue(id: Int, name: String)

object Issue {

  def from(issue: org.eclipse.egit.github.core.Issue): Issue = {
    Issue(issue.getNumber, issue.getTitle)
  }

}
