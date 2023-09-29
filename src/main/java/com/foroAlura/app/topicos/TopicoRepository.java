package com.foroAlura.app.topicos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topicos, Long> {

    List<Topicos> findByAutor(String autor);

    List<Topicos> findByCurso(String curso);

}
