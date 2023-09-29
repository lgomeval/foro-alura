package com.foroAlura.app.usuarios;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    @Transient
    private String passwordSinEncriptar;

    private String password;

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(10);

    // Contructor
    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {

        this.email = datosRegistroUsuario.email();
        this.username = datosRegistroUsuario.username();
        this.passwordSinEncriptar = datosRegistroUsuario.password();
        this.password = PASSWORD_ENCODER.encode(datosRegistroUsuario.password());
        // this.password = datosRegistroUsuario.password();
    }

    @Override
    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.passwordSinEncriptar = password;
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @Override
    public String getUsername() {

        return username;
    }

    public String getEmail() {

        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public boolean isAccountNonExpired() {

        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {

        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {

        throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {

        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}
