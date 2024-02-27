package repository

import model.*
import doobie._
import doobie.implicits._
import doobie.syntax.SqlInterpolator.SingleFragment


class ProyectoRepositorio {
  def insert(proyecto: Proyecto): ConnectionIO[Int] =
    sql"INSERT INTO Proyecto (ID, nombre, descripcion, fechaInicio) VALUES (${proyecto.ID}, ${proyecto.nombre}, ${proyecto.descripcion}, ${proyecto.fechaInicio})"
      .update
      .run
  
}



