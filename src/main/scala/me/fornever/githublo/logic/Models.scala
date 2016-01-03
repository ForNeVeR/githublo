package me.fornever.githublo.logic

import org.eclipse.egit.github.core.Issue
import org.trello4j.model.Card

case class UserModel(name: String)
case class ListModel(id: Option[String], name: String)

case class CardModel(id: Option[String], title: String, description: String, list: ListModel, assignee: Option[UserModel])
case class IssueModel(id: Option[Int], title: String, description: String, open: Boolean, assignee: Option[UserModel])

object ListModel {

  def from(list: org.trello4j.model.List) = {
    ListModel(Some(list.getId), list.getName)
  }

}

object CardModel {

  def from(card: Card, lists: Map[String, ListModel]): CardModel = {
    val list = lists(card.getIdList)
    CardModel(Some(card.getId), card.getName, card.getDesc, list, None) // TODO: Get assignee
  }

}

object IssueModel {

  def from(issue: Issue): IssueModel = {
    val state = issue.getState == "open" // TODO: Test it.
    IssueModel(Some(issue.getNumber), issue.getTitle, issue.getBodyText, state, None) // TODO: Get assignee
  }

}
