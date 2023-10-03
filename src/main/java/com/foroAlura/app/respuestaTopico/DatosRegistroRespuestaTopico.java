package com.foroAlura.app.respuestaTopico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuestaTopico(
        @NotBlank String mensaje,
        @NotBlank String autor,
        Integer topico_id,
        @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC") String fecha_creacion) {

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

}