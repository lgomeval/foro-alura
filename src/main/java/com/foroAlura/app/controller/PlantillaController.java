package com.foroAlura.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PlantillaController {

    @GetMapping("/plantilla")
    public String plantilla() {

        return "plantilla";
    }

}
