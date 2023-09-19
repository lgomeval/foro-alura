package com.foroAlura.app.topicos;

import io.micrometer.common.lang.NonNull;

public record DatosActualizarTopico(@NonNull Long id, String titulo, String mensaje, String autor, String curso,
        String estatus) {

}
