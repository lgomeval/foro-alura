package com.foroAlura.app.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password) {

}
