package com.foroAlura.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.foroAlura.app.topicos.TopicoRepository;
import com.foroAlura.app.topicos.Topicos;

@Controller
public class TopicosController {

    @Autowired
    TopicoRepository topicorepository;

    @GetMapping("/topicos")
    public String leer(Model model) {

        List<Topicos> topicos = topicorepository.findAll();
        model.addAttribute("topicos", topicos);

        return "topicos";
    }

    // Para agregar topicos mostramos el formulario y enviamos los datos

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {

        model.addAttribute("Topico", new Topicos());
        return "crear-topicos";
    }

    @PostMapping("crear-topicos")
    public String agregar(@ModelAttribute("Topico") @RequestBody Topicos nuevoTopicos) {

        System.out.println(nuevoTopicos);
        topicorepository.save(nuevoTopicos);

        return "redirect:/topicos";
    }
}
