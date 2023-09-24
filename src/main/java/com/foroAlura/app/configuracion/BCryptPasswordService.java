package com.foroAlura.app.configuracion;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(String plainPassword) {

        return passwordEncoder.encode(plainPassword);
    }

    public boolean cheakPassword(String plainPassword, String hashedPassword) {

        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
