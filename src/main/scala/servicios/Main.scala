import cats.effect.{ExitCode, IO, IOApp}
import servicios.HttpServer

object ServerApp extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    HttpServer.create()
  }
}