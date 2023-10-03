package com.foroAlura.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foroAlura.app.respuestaTopico.DatosRegistroRespuestaTopico;
import com.foroAlura.app.respuestaTopico.RespuestaTopico;
import com.foroAlura.app.respuestaTopico.RespuestaTopicoRepository;
import com.foroAlura.app.topicos.TopicoRepository;
import com.foroAlura.app.topicos.Topicos;
import com.foroAlura.app.usuarios.UsuarioRepository;

@Controller
@RequestMapping
public class RespuestaTopicoController {

    @Autowired
    RespuestaTopicoRepository respuestatopicorepository;

    @Autowired
    TopicoRepository topicorepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    // Metodo que muestra el formulario para guardar la respuesta en la bbdd

    @GetMapping("/reaccionar/{id}")
    public String mostrarFormularioRespuestaTopico(@PathVariable Long id, Model model) {

        // Obtener el nombre de usuario del usuario autenticado
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Obtener el ID del usuario desde tu repositorio o servicio (dependiendo de tu
        // configuración)
        Long usuarioId = usuarioRepository.obtenerIdPorNombre(username);

        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("idTopico", id);
        return "crear-respuesta-topico";
    }

    // Metodo que guardar la respuesta en la BBDD
    @PostMapping("/crear-respuesta-topico")
    public String guardarRespuestaTopico(@ModelAttribute DatosRegistroRespuestaTopico datosRegistroRespuestaTopico,
            Model model, RedirectAttributes redirectAttributes) {

        // Obtener el valor de topico_id del formulario
        Integer topicoId = datosRegistroRespuestaTopico.topico_id();

        Topicos topicos = topicorepository.findById(topicoId);

        if (topicos == null) {
            // Manejar el caso en el que no se encuentra el tópico
            redirectAttributes.addFlashAttribute("error", "El tópico no existe");
            return "redirect:/"; // Redirigir a la página principal o donde corresponda
        }

        // Crear una instancia de RespuestaTopico
        RespuestaTopico respuestaTopico = new RespuestaTopico(datosRegistroRespuestaTopico);

        // Asignar el valor de topico_id a la instancia de RespuestaTopico
        respuestaTopico.setTopico_id(topicos);

        // Lógica para guardar la respuesta al tópico
        respuestatopicorepository.save(respuestaTopico);
        redirectAttributes.addFlashAttribute("success", "Tópico Agregadado Existosamente");

        // Puedes utilizar el ID para asociar la respuesta al tópico correspondiente
        // Luego redirige al usuario a la página de detalles del tópico

        return "redirect:/";
    }
}
