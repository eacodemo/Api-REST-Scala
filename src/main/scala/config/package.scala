import cats.effect.{IO, Resource}
import com.typesafe.config.ConfigFactory
import pureconfig.ConfigSource
import pureconfig.*
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.*

package object config {

  case class ServerConfig(host: String, port: Int)

  case class DatabaseConfig(driver: String, url: String, user: String, password: String, threadPoolSize: Int)

  case class Config(server: ServerConfig, database: DatabaseConfig)


  object Config {
    // Derivar ConfigReader automáticamente usando semiauto
    implicit val serverConfigReader: ConfigReader[ServerConfig] = ConfigReader[ServerConfig]
    implicit val databaseConfigReader: ConfigReader[DatabaseConfig] = ConfigReader[DatabaseConfig]
    implicit val configReader: ConfigReader[Config] = ConfigReader[Config]

    def load(configFile: String = "application.conf"): Resource[IO, Config] = {
      Resource.eval(IO(ConfigSource.fromConfig(ConfigFactory.load(configFile)).load[Config] match {
        case Left(failures) => throw new RuntimeException(failures.toString)
        case Right(config) => config
      }))
    }
  }
}
