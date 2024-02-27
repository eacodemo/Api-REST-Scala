//package repository
//
//import model.Usuario
//import doobie.implicits.toSqlInterpolator
//import doobie.syntax.all.toSqlInterpolator
//import doobie.syntax.string.toSqlInterpolator
//
//// Interface for Usuario data getthering
////trait UsuarioRepositorio:
////  def insert(usuario: Usuario): ConnectionIO[Int]
////  def getAll: ConnectionIO[List[Usuario]]
////  def getById(id: Int): ConnectionIO[Option[Usuario]]
////  def update(usuario: Usuario): ConnectionIO[Int]
////  def deleteById(id: Int): ConnectionIO[Int]
//
//// This is an UsuarioRepositorio implementation
//// which is talking with DB/SQL
//class UsuarioRepositorioImpl{ //extends UsuarioRepositorio {
//
//  def insert(usuario: Usuario) =
//    sql"INSERT INTO Usuario (ID, nombreUsuario, email, contraseña, tipo) VALUES (${usuario.ID}, ${usuario.nombreUsuario}, ${usuario.email}, ${usuario.contraseña}, ${usuario.tipo})"
//
////  def getAll =
////    sql"SELECT ID, nombreUsuario, email, contraseña, tipo FROM Usuario"
////      .query[Usuario]
////      .to[List]
////
////  def getById(id: Int) =
////    sql"SELECT ID, nombreUsuario, email, contraseña, tipo FROM Usuario WHERE ID = $id"
////      .query[Usuario]
////      .option
////
////  def update(usuario: Usuario) =
////    sql"UPDATE Usuario SET nombreUsuario = ${usuario.nombreUsuario}, email = ${usuario.email}, contraseña = ${usuario.contraseña}, tipo = ${usuario.tipo} WHERE ID = ${usuario.ID}"
////      .update
////      .run
////
////  def deleteById(id: Int)=
////    sql"DELETE FROM Usuario WHERE ID = $id"
////      .update
////      .run
//}
