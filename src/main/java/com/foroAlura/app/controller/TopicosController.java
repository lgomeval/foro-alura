package com.foroAlura.app.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foroAlura.app.respuestaTopico.RespuestaTopico;
import com.foroAlura.app.respuestaTopico.RespuestaTopicoRepository;
import com.foroAlura.app.topicos.DatosActualizarTopico;
import com.foroAlura.app.topicos.DatosRegistroTopico;
import com.foroAlura.app.topicos.TopicoRepository;
import com.foroAlura.app.topicos.Topicos;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class TopicosController {

    @Autowired
    TopicoRepository topicorepository;

    @Autowired
    RespuestaTopicoRepository respuestatopicorepository;

    // Listamos todos los topicos existentes
    @GetMapping("/")
    public String listarTopicos(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Topicos> topicos = topicorepository.findAll();
        List<RespuestaTopico> respuestaTopicos = respuestatopicorepository.findAll();

        model.addAttribute("respuestatopicos", respuestaTopicos);
        model.addAttribute("topicos", topicos);
        model.addAttribute("usuarios", username);

        return "/topicos";
    }

    // Listamos un topico filtrado por id, por autor, curso

    @GetMapping("/mostrarTopico")
    public String mostrarTopico(@RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "autor", required = false) String autor,
            @RequestParam(name = "curso", required = false) String curso,
            Model model) {

        if (id != null) {
            Topicos topicos = topicorepository.findById(id).orElse(null);

            if (topicos == null) {
                return "Error404";
            }

            model.addAttribute("topicos", topicos);

            // Mostrar la respuesta del topico

            List<RespuestaTopico> respuestaTopico = respuestatopicorepository.findByTopicosId(id);

            model.addAttribute("respuestaTopico", respuestaTopico);

            // fin de respuesta topico

            return "mostrarTopico";

        } else if (autor != null) {

            List<Topicos> topicosByAutor = topicorepository.findByAutor(autor);
            model.addAttribute("topicosPorAutor", topicosByAutor);

            return "mostrarTopicosPorAutor";

        } else if (curso != null) {

            List<Topicos> topicosByCurso = topicorepository.findByCurso(curso);
            model.addAttribute("topicoPorCurso", topicosByCurso);
            return "mostrarTopicosPorCurso";

        } else {

            return "error404";
        }

    }

    // Para agregar topicos mostramos el formulario y enviamos los datos

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {

        model.addAttribute("Topico", new Topicos());
        return "/crear-topicos";
    }

    @PostMapping("/crear-topicos")
    public String agregar(@ModelAttribute @Valid DatosRegistroTopico datosRegistroTopico,
            RedirectAttributes redirectAttributes) {

        topicorepository.save(new Topicos(datosRegistroTopico));
        // Agrega un mensaje flash
        redirectAttributes.addFlashAttribute("success", "Tópico Agregadado Existosamente");

        return "redirect:/";
    }

    // Metodo para Actualizar un Topico

    @GetMapping("/actualizar")
    public String formularioActualizar(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);
        if (topicos == null) {
            return "Error404";
        }
        model.addAttribute("topicos", topicos);
        return "/actualizar-topicos";
    }

    @PostMapping("/actualizar-topicos")
    public String actualizarTopico(@ModelAttribute @Valid DatosActualizarTopico datosActulizartopicos,
            RedirectAttributes redirectAttributes) {

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
            redirectAttributes.addFlashAttribute("warning", "Tópico Modificado Existosamente");
            topicorepository.save(topicos);
        }

        return "redirect:/";
    }

    // Metodo para eliminar un Topico
    @GetMapping("/eliminar-topico")
    public String confirmarEliminacion(@RequestParam Long id, Model model) {

        Topicos topicos = topicorepository.findById(id).orElse(null);

        model.addAttribute("topicos", topicos);

        if (topicos == null) {
            return "error-404";
        }

        return "/eliminar-topico";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        topicorepository.deleteById(id);
        redirectAttributes.addFlashAttribute("danger", "Tópico Eliminado Existosamente");

        return "redirect:/";
    }
}
