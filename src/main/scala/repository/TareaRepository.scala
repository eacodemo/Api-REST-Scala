package repository

import model.*
import doobie._
import doobie.implicits._
import doobie.syntax.SqlInterpolator.SingleFragment

class TareaRepository {
  def insert(tarea: Tarea): ConnectionIO[Int] =
    sql"INSERT INTO Tarea (ID, descripcion, estado, IDHistoriaUsuario) VALUES (${tarea.ID}, ${tarea.descripcion}, ${tarea.estado}, ${tarea.IDHistoriaUsuario})"
      .update
      .run
}



