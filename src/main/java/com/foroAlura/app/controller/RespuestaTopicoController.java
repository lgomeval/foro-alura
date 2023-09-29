package com.foroAlura.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foroAlura.app.respuestaTopico.DatosRespuestaTopico;
import com.foroAlura.app.respuestaTopico.RespuestaService;
import com.foroAlura.app.respuestaTopico.RespuestaTopico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/respuesta")
public class RespuestaTopicoController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public List<DatosRespuestaTopico> getRespuesta() {

        return respuestaService.getRespuesta();
    }

    @GetMapping("usuario/{id}")
    public List<DatosRespuestaTopico> getRespuestaUsuario(@RequestParam Long id) {

        return respuestaService.getRespuestaUsuario(id);
    }

    @GetMapping("/topico/{id}")
    public List<DatosRespuestaTopico> getRespuestaTopico(@RequestParam Long id) {
        return respuestaService.getRespuestaTopico(id);
    }

    @PostMapping
    public DatosRespuestaTopico CrearRespuesta(@RequestBody @Valid RespuestaTopico respuestaTopico) {
        return respuestaService.CrearRespuesta(respuestaTopico);
    }

    @DeleteMapping("/{id}")
    public String eliminarRespuesta(@RequestParam Long id) {
        respuestaService.eliminarRespuesta(id);

        return "/";
    }

    // @GetMapping("mostrarTopico/{id}")
    // public String mostrarRespuestaTopico(@RequestParam Long id, Model model) {

    // RespuestaTopico respuestaTopico =
    // respuestaTopicoRepository.findById(id).orElse(null);

    // if (respuestaTopico == null) {
    // return "Error404";
    // }

    // return "mostrarTopico";
    // }
}
