package services

import doobie.hikari.HikariTransactor
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.circe.CirceEntityCodec._
import cats.effect.IO
import doobie.implicits.toConnectionIOOps
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import io.circe.generic.auto._
import model.Usuario
import repository.TodoRepository

object UsuarioRoutes {
  def routes(transactor: HikariTransactor[IO]): HttpRoutes[IO] =
    HttpRoutes.of[IO] {
      case req @ POST -> Root / "usuarios" =>
        req.decode[Usuario] { usuario =>
          TodoRepository().insert(usuario).transact(transactor).attempt.flatMap {
            case Right(1) => Ok("Usuario insertado correctamente")
            case Right(_) => InternalServerError("Error al insertar el usuario: No se pudo determinar el número de filas afectadas")
            case Left(ex) => InternalServerError(s"Error al insertar el usuario: ${ex.getMessage}")
          }
        }
    }
}

