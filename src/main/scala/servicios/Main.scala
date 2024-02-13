package servicios


import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = Api_restServer.run[IO]


