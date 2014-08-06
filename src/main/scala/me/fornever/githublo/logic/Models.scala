package me.fornever.githublo.logic

case class UserModel(name: String)
case class ListModel(name: String)

case class IssueModel(title: String, description: String, open: Boolean, assignee: Option[UserModel])
case class CardModel(title: String, description: String, list: ListModel, assignee: Option[UserModel])