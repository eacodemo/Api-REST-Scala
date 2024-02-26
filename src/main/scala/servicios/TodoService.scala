package servicios

import cats.effect.IO
import doobie.util.transactor.Transactor
import io.circe.generic.auto.*
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import org.http4s.{EntityDecoder, EntityEncoder, HttpRoutes}
import repository.TodoRepository
import model.Usuario
import doobie.{ConnectionIO, Transactor}
import doobie.implicits.toConnectionIOOps
import doobie.syntax.all.toConnectionIOOps
import doobie.syntax.connectionio.toConnectionIOOps

class TodoService(repository: TodoRepository, xa: Transactor[IO]) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req@POST -> Root / "usuarios" =>
      req.decode[Usuario] { usuario =>
        repository.insert(usuario).transact(xa).flatMap { _ =>
          Created()
        }
      }
  }
}
