package com.foroAlura.app.respuestaTopico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaTopicoRepository respuestaTopicoRepository;

    public List<DatosRespuestaTopico> getRespuesta() {

        List<RespuestaTopico> respuestaTopico = respuestaTopicoRepository.findAll();
        return respuestaTopico.stream().map(DatosRespuestaTopico::new).collect(Collectors.toList());
    }

    public List<DatosRespuestaTopico> getRespuestaUsuario(Long id) {

        List<RespuestaTopico> respuestaTopico = respuestaTopicoRepository.findAllByUsuarioId(id);
        return respuestaTopico.stream().map(DatosRespuestaTopico::new).collect(Collectors.toList());
    }

    public List<DatosRespuestaTopico> getRespuestaTopico(Long id) {

        List<RespuestaTopico> respuestaTopico = respuestaTopicoRepository.findAllByTopicosId(id);
        return respuestaTopico.stream().map(DatosRespuestaTopico::new).collect(Collectors.toList());
    }

    public DatosRespuestaTopico CrearRespuesta(@Valid RespuestaTopico respuestaTopico) {

        if (respuestaTopico.getId() == null) {
            respuestaTopico.setCrear_fecha(LocalDateTime.now());
        } else {
            RespuestaTopico guardarRespuesta = (RespuestaTopico) respuestaTopicoRepository
                    .findAllByTopicosId(respuestaTopico.getId());
            respuestaTopico.setCrearRespuesta(guardarRespuesta.getMensaje());
        }
        return new DatosRespuestaTopico(respuestaTopicoRepository.save(respuestaTopico));
    }

    public void eliminarRespuesta(Long id) {

        respuestaTopicoRepository.deleteById(id);
    }

    public RespuestaTopico findByRespuestaTopicoId(Long id) {
        return null;
    }

}
