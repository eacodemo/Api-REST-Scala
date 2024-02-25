package model

import java.sql.Date

case class Usuario(ID: Int, nombreUsuario: String, email: String, contraseña: String, tipo: String)

case class Proyecto(ID: Int, nombre: String, descripcion: Option[String], fechaInicio: Option[Date])
