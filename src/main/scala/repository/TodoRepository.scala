package repository

import doobie._
import doobie.implicits._
import model._
class TodoRepository {
  def insert(usuario: Usuario): ConnectionIO[Int] =
    sql"INSERT INTO Usuario (ID, nombreUsuario, email, contraseña, tipo) VALUES (${usuario.ID}, ${usuario.nombreUsuario}, ${usuario.email}, ${usuario.contraseña}, ${usuario.tipo})"
      .update
      .run
}



