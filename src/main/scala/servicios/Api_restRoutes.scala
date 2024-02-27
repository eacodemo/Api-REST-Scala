package services

import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.circe.CirceEntityCodec.*
import cats.effect.IO
import doobie.implicits.toConnectionIOOps
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import io.circe.generic.auto.*
import model.Usuario
import repository.{UsuarioRepositorio, UsuarioRepositorioImpl}
import doobie.util.transactor.Transactor

class UsuarioService(transactor: Transactor[IO]) {
  private val usuarioRepo: UsuarioRepositorio = new UsuarioRepositorioImpl

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
