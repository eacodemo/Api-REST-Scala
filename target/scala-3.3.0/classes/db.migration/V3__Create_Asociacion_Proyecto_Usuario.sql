CREATE TABLE AsociacionProyectoUsuario (
    id INT PRIMARY KEY,
    idusuario INT,
    idproyecto INT,
    rol VARCHAR(256) NOT NULL,
    FOREIGN KEY (idproyecto) REFERENCES Proyecto(id)
    FOREIGN KEY (idusuario) REFERENCES Usuario(id)
);
