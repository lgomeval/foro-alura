package com.foroAlura.app.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    Long obtenerIdPorNombre(@Param("email") String username);

}
