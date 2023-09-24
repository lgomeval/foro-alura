package com.foroAlura.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foroAlura.app.configuracion.BCryptPasswordService;
import com.foroAlura.app.usuarios.DatosAutenticacionUsuario;
import com.foroAlura.app.usuarios.Usuario;
import com.foroAlura.app.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordService passwordService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarFormularioDeLogin() {

        return "login";
    }

    @PostMapping("/login")
    public String autenticarnUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario) {

        Usuario usuario = usuarioRepository.findByEmail(datosAutenticacionUsuario.email());

        if (usuario != null
                && passwordService.cheakPassword(datosAutenticacionUsuario.password(), usuario.getPassword())) {

            Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                    datosAutenticacionUsuario.password());

            Authentication authentication = authenticationManager.authenticate(token);

            if (authentication.isAuthenticated()) {

                return "redirect:/topicos/topicos";

            }
        }

        return "login";
    }

    @GetMapping(value = "registro")
    public String mostrarFormularioDeRegistro() {

        return "autorizacion/registro";
    }
}
