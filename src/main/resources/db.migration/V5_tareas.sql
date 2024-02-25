CREATE TABLE Tarea (
    id INT PRIMARY KEY, 
    descripcion TEXT,
    estado VARCHAR(15) NOT NULL ,
    idHistoriaUsuario INT,
    FOREIGN KEY (idHistoriaUsuario) REFERENCES HistoriaUsuario(id)
);
