CREATE Table respuesta_topicos (

    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATE NOT NULL,
    autor VARCHAR(100) NOT NULL,
    topico_id BIGINT NOT NULL, 

    PRIMARY KEY(id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)    
);