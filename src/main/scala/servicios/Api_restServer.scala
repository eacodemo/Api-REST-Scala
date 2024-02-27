package servicios

import org.http4s.dsl.io.*

case class Port(number: Int) extends AnyVal
sealed trait AuthMethod
case class Login(username: String, password: String) extends AuthMethod
case class Token(token: String) extends AuthMethod
case class PrivateKey(pkFile: java.io.File) extends AuthMethod

case class ServiceConf(
                        host: String,
                        port: Port,
                        useHttps: Boolean,
                        authMethods: List[AuthMethod]
                      )

