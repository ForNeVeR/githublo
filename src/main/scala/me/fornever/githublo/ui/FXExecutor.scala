package me.fornever.githublo.ui

import concurrent.{ExecutionContextExecutor, ExecutionContext}
import java.util.Collections
import java.util.concurrent.{TimeUnit, AbstractExecutorService}
import scalafx.application.Platform

object FXExecutor {

  implicit lazy val executor: ExecutionContextExecutor = ExecutionContext.fromExecutorService(FXExecutorService)

}

object FXExecutorService extends AbstractExecutorService {
  def execute(command: Runnable) = Platform.runLater(command)

  def shutdown(): Unit = ()

  def shutdownNow() = Collections.emptyList[Runnable]

  def isShutdown = false

  def isTerminated = false

  def awaitTermination(l: Long, timeUnit: TimeUnit) = true
}