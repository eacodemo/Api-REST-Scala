package repository

import model.*
import doobie._
import doobie.implicits._
import doobie.syntax.SqlInterpolator.SingleFragment

class AsociacionRepository {
  def insert(asociacion: AsociacionProyectoUsuario): ConnectionIO[Int] =
    sql"INSERT INTO AsociacionProyectoUsuario (ID, IDProyecto, IDUsuario, Rol) VALUES (${asociacion.ID}, ${asociacion.IDProyecto}, ${asociacion.IDUsuario}, ${asociacion.Rol})"
      .update
      .run
}



