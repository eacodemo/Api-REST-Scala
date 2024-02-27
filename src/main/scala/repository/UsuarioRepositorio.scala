package repository

import doobie._
import doobie.implicits._
import model._
class UsuarioRepositorio {
  def insert(usuario: Usuario): ConnectionIO[Int] =
    sql"INSERT INTO Usuario (ID, nombreUsuario, email, contraseña, tipo) VALUES (${usuario.ID}, ${usuario.nombreUsuario}, ${usuario.email}, ${usuario.contraseña}, ${usuario.tipo})"
      .update
      .run

  def getAll: ConnectionIO[List[Usuario]] =
    sql"SELECT ID, nombreUsuario, email, contraseña, tipo FROM Usuario"
      .query[Usuario]
      .to[List]

  def getById(id: Int): ConnectionIO[Option[Usuario]] =
    sql"SELECT ID, nombreUsuario, email, contraseña, tipo FROM Usuario WHERE ID = $id"
      .query[Usuario]
      .option

  def update(usuario: Usuario): ConnectionIO[Int] =
    sql"UPDATE Usuario SET nombreUsuario = ${usuario.nombreUsuario}, email = ${usuario.email}, contraseña = ${usuario.contraseña}, tipo = ${usuario.tipo} WHERE ID = ${usuario.ID}"
      .update
      .run

  def deleteById(id: Int): ConnectionIO[Int] =
    sql"DELETE FROM Usuario WHERE ID = $id"
      .update
      .run  
}



