ALTER TABLE topicos
ADD CONSTRAINT campo_unico_titulo
UNIQUE (titulo);

ALTER TABLE topicos
ADD CONSTRAINT campo_unico_mensaje
UNIQUE (mensaje);