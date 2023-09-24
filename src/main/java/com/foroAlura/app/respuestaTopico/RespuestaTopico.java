package com.foroAlura.app.respuestaTopico;

import com.foroAlura.app.topicos.Topicos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "respuesta_topicos")
@Entity(name = "RespuestaTopicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespuestaTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private String autor;
    private String fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topicos topicos;

    public void setTieneRespuestas(boolean tieneRespuestas) {
    }

}
