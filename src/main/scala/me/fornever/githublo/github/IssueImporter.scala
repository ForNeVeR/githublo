package me.fornever.githublo.github

class IssueImporter(owner: String, repo: String) {

  def loadIssues(): Stream[Issue] = {
    throw new NotImplementedError()
  }

}
