CREATE TABLE HistoriaUsuario (
    id INT PRIMARY KEY,
    detalles TEXT,
    criteriosAceptacion TEXT.
    estado VARCHAR(15) NOT NULL,
    idTareaAsociada INT,
    idProyecto INT,
    FOREIGN KEY (idProyecto) REFERENCES Proyecto(id),
    FOREIGN KEY (idTareaAsociada) REFERENCES Tarea(id),
);
