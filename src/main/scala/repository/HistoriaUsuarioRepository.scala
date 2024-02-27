package repository

import model.*
import doobie._
import doobie.implicits._
import doobie.syntax.SqlInterpolator.SingleFragment

class HistoriaUsuarioRepository {
  def insert(historia: HistoriaUsuario): ConnectionIO[Int] =
    sql"INSERT INTO HistoriaUsuario (ID, detalles, criteriosAceptacion, estado, IDProyecto, IDTareaAsociada) VALUES (${historia.ID}, ${historia.detalles}, ${historia.criteriosAceptacion}, ${historia.estado}, ${historia.IDProyecto}, ${historia.IDTareaAsociada})"
      .update
      .run
}



