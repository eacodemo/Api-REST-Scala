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
import repository.UsuarioRepositorio
import doobie.util.transactor.Transactor

class UsuarioService(transactor: Transactor[IO]) {
  private val usuarioRepo = new UsuarioRepositorio

  val usuarioRoutes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "usuarios" =>
      Ok(usuarioRepo.getAll.transact(transactor))

    case GET -> Root / "usuarios" / IntVar(id) =>
      Ok(usuarioRepo.getById(id).transact(transactor))

    case req@POST -> Root / "usuarios" =>
      req.decode[Usuario] { usuario =>
        Created(usuarioRepo.insert(usuario).transact(transactor))
      }

    case req@PUT -> Root / "usuarios" =>
      req.decode[Usuario] { usuario =>
        Ok(usuarioRepo.update(usuario).transact(transactor))
      }

    case DELETE -> Root / "usuarios" / IntVar(id) =>
      Ok(usuarioRepo.deleteById(id).transact(transactor))
  }
}
object UsuarioRoutes {
  def routes(transactor: HikariTransactor[IO]): HttpRoutes[IO] =
    HttpRoutes.of[IO] {
      case req @ POST -> Root / "usuarios" =>
        req.decode[Usuario] { usuario =>
          UsuarioRepositorio().insert(usuario).transact(transactor).attempt.flatMap {
            case Right(1) => Ok("Usuario insertado correctamente")
            case Right(_) => InternalServerError("Error al insertar el usuario: No se pudo determinar el número de filas afectadas")
            case Left(ex) => InternalServerError(s"Error al insertar el usuario: ${ex.getMessage}")
          }
        }
    }
}

