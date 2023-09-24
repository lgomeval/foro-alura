package com.foroAlura.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foroAlura.app.respuestaTopico.RespuestaTopico;
import com.foroAlura.app.respuestaTopico.RespuestaTopicoRepository;
import com.foroAlura.app.topicos.DatosActualizarTopico;
import com.foroAlura.app.topicos.DatosRegistroTopico;
import com.foroAlura.app.topicos.TopicoRepository;
import com.foroAlura.app.topicos.Topicos;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicorepository;

    @Autowired
    RespuestaTopicoRepository respuestatopicorepository;

    // Listamos todos los topicos existentes
    @GetMapping
    public String listarTopicos(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Topicos> topicos = topicorepository.findAll();
        List<RespuestaTopico> respuestaTopicos = respuestatopicorepository.findAll();

        model.addAttribute("respuestatopicos", respuestaTopicos);
        model.addAttribute("topicos", topicos);
        model.addAttribute("usuarios", username);

        return "topicos/topicos";
    }

    // Listamos un topico filtrado por id

    @GetMapping("/mostrarTopico")
    public String mostrarTopico(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);
        if (topicos == null) {
            return "Error404";
        }

        // MOstrar la respuesta del topico

        RespuestaTopico respuestaTopico = respuestatopicorepository.findById(id).orElse(null);
        if (respuestaTopico == null) {
            return "Error404";
        }

        model.addAttribute("respuestaTopico", respuestaTopico);

        // fin de respuesta topico

        model.addAttribute("topicos", topicos);
        return "topicos/mostrarTopico";
    }

    // Para agregar topicos mostramos el formulario y enviamos los datos

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {

        model.addAttribute("Topico", new Topicos());
        return "topicos/crear-topicos";
    }

    @PostMapping("/crear-topicos")
    public String agregar(@ModelAttribute @Valid DatosRegistroTopico datosRegistroTopico) {

        topicorepository.save(new Topicos(datosRegistroTopico));

        return "redirect:/topicos/topicos";
    }

    // Metodo para Actualizar un Topico

    @GetMapping("/actualizar")
    public String formularioActualizar(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);
        if (topicos == null) {
            return "Error404";
        }
        model.addAttribute("topicos", topicos);
        return "topicos/actualizar-topicos";
    }

    @PostMapping("/actualizar-topicos")
    public String actualizarTopico(@ModelAttribute @Valid DatosActualizarTopico datosActulizartopicos) {

        Optional<Topicos> optionalTopicos = topicorepository.findById(datosActulizartopicos.id());

        if (optionalTopicos.isPresent()) {

            Topicos topicos = optionalTopicos.get();

            if (datosActulizartopicos.titulo() != null) {
                topicos.setTitulo(datosActulizartopicos.titulo());
            }

            if (datosActulizartopicos.mensaje() != null) {
                topicos.setMensaje(datosActulizartopicos.mensaje());
            }

            if (datosActulizartopicos.autor() != null) {
                topicos.setAutor(datosActulizartopicos.autor());
            }

            if (datosActulizartopicos.curso() != null) {
                topicos.setCurso(datosActulizartopicos.curso());
            }

            if (datosActulizartopicos.estatus() != null) {
                topicos.setEstatus(datosActulizartopicos.estatus());
            }

            topicorepository.save(topicos);
        }

        return "redirect:/topicos/topicos";
    }

    // Metodo para eliminar un Topico
    @GetMapping("/eliminar-topico")
    public String confirmarEliminacion(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);

        model.addAttribute("topicos", topicos);

        if (topicos == null) {
            return "error-404";
        }

        return "topicos/eliminar-topico";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {

        topicorepository.deleteById(id);

        return "redirect:/topicos";
    }

    // Respuesta de un topico
    // @GetMapping("/mostrarRespuestaTopico")
    // public String mostrarRespuestaTopico(@RequestParam Long id, Model model) {

    // List<RespuestaTopico> respuestaTopicos =
    // respuestatopicorepository.findAllByTopicosId(id);
    // model.addAttribute("respuestaTopico", respuestaTopicos);

    // return "topicos/mostrarTopico";
    // }
}
