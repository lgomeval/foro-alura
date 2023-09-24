package com.foroAlura.app.respuestaTopico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaTopicoRepository extends JpaRepository<RespuestaTopico, Long> {

    List<RespuestaTopico> findAllByTopicosId(Long id);
}
