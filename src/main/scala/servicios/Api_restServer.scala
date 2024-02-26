package servicios

import org.http4s.dsl.io.*
import cats.effect.*
import db.Database
import doobie.hikari.{Config => HikariConfig, HikariTransactor}
import doobie.util.ExecutionContexts
import org.http4s.implicits.*
import org.http4s.blaze.server.BlazeServerBuilder
import repository.TodoRepository

object HttpServer {
  def create(configFile: String = "application.conf"): IO[ExitCode] = {
    resources(configFile).use(create)
  }

  private def resources(configFile: String): Resource[IO, Resources] = {
    for {
      config <- ServiceConf.load(configFile)
      ec <- ExecutionContexts.fixedThreadPool[IO](config.database.threadPoolSize)
      transactor <- Database.transactor(config.database, ec)
    } yield Resources(transactor, config)
  }

  private def create(resources: Resources): IO[ExitCode] = {
    for {
      _ <- Database.initialize(resources.transactor)
      repository = new TodoRepository(resources.transactor)
      exitCode <- BlazeServerBuilder[IO]
        .bindHttp(resources.config.server.port.number, resources.config.server.host)
        .withHttpApp(new TodoService(repository).routes.orNotFound).serve.compile.lastOrError
    } yield exitCode
  }

  case class Resources(transactor: HikariTransactor[IO], config: ServiceConf)
}