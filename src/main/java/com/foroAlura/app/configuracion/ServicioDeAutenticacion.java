package com.foroAlura.app.configuracion;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foroAlura.app.usuarios.Usuario;
import com.foroAlura.app.usuarios.UsuarioRepository;

@Service
public class ServicioDeAutenticacion implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {

            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), Collections.emptyList());
    }

}
