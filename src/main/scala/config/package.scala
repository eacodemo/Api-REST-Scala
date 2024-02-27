package object config {
  case class ServerConfig(host: String ,port: Int)
  case class DatabaseConfig(driver: String, url: String, user: String, password: String, threadPoolSize: Int)
  case class Config(server: ServerConfig, database: DatabaseConfig)
  object Config{
    def load(): Config =
      Config(
        server = ServerConfig(
          host = "",
          port = 1010
        ),
        database = DatabaseConfig(
          driver = "",
          url = "",
          user = "",
          password = "",
          threadPoolSize = 32
        )
      )
  }
}
