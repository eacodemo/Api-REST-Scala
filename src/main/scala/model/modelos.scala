package model

import java.sql.Date

case class Usuario(ID: Int, nombreUsuario: String, email: String, contraseña: String, tipo: String)
case class Proyecto(ID: Int, nombre: String, descripcion: Option[String], fechaInicio: Option[Date])
case class AsociacionProyectoUsuario(ID: Int, IDProyecto: Int, IDUsuario: Int, Rol: String)
case class HistoriaUsuario(ID: Int, detalles: String, criteriosAceptacion: String, estado: String, IDProyecto: Int, IDTareaAsociada: Option[Int])
case class Tarea(ID: Int, descripcion: String, estado: String, IDHistoriaUsuario: Int)
