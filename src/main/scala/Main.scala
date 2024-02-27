import cats.effect.{IO, IOApp}
object Main extends IOApp.Simple:
  val run = HttpServer.run[IO]
