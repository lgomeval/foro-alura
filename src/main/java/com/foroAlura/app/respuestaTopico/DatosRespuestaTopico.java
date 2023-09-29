package com.foroAlura.app.respuestaTopico;

public record DatosRespuestaTopico(
        Long id,
        String respuestaTopico,
        DatosRespuestaTopico datosRespuestaTopico) {

    public DatosRespuestaTopico(RespuestaTopico respuestaTopico) {
        this(respuestaTopico.getId(), null, null);
    }
}
