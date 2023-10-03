package com.foroAlura.app.topicos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
                @NotBlank String titulo,
                @NotBlank String mensaje,
                @NotBlank String autor,
                @NotBlank String curso,
                @NotBlank String estatus,
                byte[] imagen,
                @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC") String fecha_creacion) {

}
