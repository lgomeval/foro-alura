package com.foroAlura.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Listamos todos los topicos existentes
    @GetMapping
    public String listarTopicos(Model model) {

        List<Topicos> topicos = topicorepository.findAll();
        model.addAttribute("topicos", topicos);

        return "topicos";
    }

    // Listamos un topico filtrado por id

    @GetMapping("/mostrarTopico")
    public String mostrarTopico(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);
        if (topicos == null) {
            return "Error404";
        }
        model.addAttribute("topicos", topicos);
        return "mostrarTopico";
    }

    // Para agregar topicos mostramos el formulario y enviamos los datos

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {

        model.addAttribute("Topico", new Topicos());
        return "crear-topicos";
    }

    @PostMapping("/crear-topicos")
    public String agregar(@ModelAttribute @Valid DatosRegistroTopico datosRegistroTopico) {

        topicorepository.save(new Topicos(datosRegistroTopico));

        return "redirect:/topicos";
    }

    // Metodo para Actualizar un Topico

    @GetMapping("/actualizar")
    public String formularioActualizar(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);
        if (topicos == null) {
            return "Error404";
        }
        model.addAttribute("topicos", topicos);
        return "actualizar-topicos";
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

        return "redirect:/topicos";
    }

    // Metodo para eliminar un Topico
    @GetMapping("/eliminar-topico")
    public String confirmarEliminacion(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);

        model.addAttribute("topicos", topicos);

        if (topicos == null) {
            return "error-404";
        }

        return "eliminar-topico";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {

        topicorepository.deleteById(id);

        return "redirect:/topicos";
    }

}
