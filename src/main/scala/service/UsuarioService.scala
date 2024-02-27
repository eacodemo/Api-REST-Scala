//package service
//
//import org.http4s.*
//import org.http4s.dsl.io.*
//import org.http4s.circe.CirceEntityCodec.*
//import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
//import model.Usuario
//import repository.{UsuarioRepositorio, UsuarioRepositorioImpl}
//
//class UsuarioService(usuarioRepo: UsuarioRepositorio) extends Http4sDsl[IO] {
//
//  val usuarioRoutes: HttpRoutes[IO] = HttpRoutes.of[IO] {
//    case GET -> Root / "usuarios" =>
//      Ok(usuarioRepo.getAll)
//
//    case GET -> Root / "usuarios" / IntVar(id) =>
//      Ok(usuarioRepo.getById(id))
//
//    case req@POST -> Root / "usuarios" =>
//      req.decode[Usuario] { usuario =>
//        Created(usuarioRepo.insert(usuario))
//      }
//
//    case req@PUT -> Root / "usuarios" =>
//      req.decode[Usuario] { usuario =>
//        Ok(usuarioRepo.update(usuario))
//      }
//
//    case DELETE -> Root / "usuarios" / IntVar(id) =>
//      Ok(usuarioRepo.deleteById(id))
//  }
//}
