package me.fornever.githublo.ui

import scalafxml.core.ControllerDependencyResolver
import scala.reflect.runtime.universe.Type

class ComplexDependencyResolver(resolvers: ControllerDependencyResolver*) extends ControllerDependencyResolver {

  override def get(paramName: String, dependencyType: Type): _root_.scala.Option[Any] = {
    resolvers.foldLeft(Option.empty[Any])({ (result, resolver) =>
      result.orElse(resolver.get(paramName, dependencyType))
    })
  }

}
