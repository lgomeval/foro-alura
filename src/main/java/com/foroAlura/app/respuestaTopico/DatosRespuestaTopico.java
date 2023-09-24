package com.foroAlura.app.respuestaTopico;

import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaTopico(

                @NotBlank String mensaje) {
}
