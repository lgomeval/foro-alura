package com.foroAlura.app.respuestaTopico;

import com.foroAlura.app.topicos.Topicos;
import com.foroAlura.app.usuarios.Usuario;

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
@Entity(name = "RespuestaTopico")
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Contructor
    public RespuestaTopico(DatosRegistroRespuestaTopico datosRegistroRespuestaTopico) {
        this.mensaje = datosRegistroRespuestaTopico.getMensaje();
        this.autor = datosRegistroRespuestaTopico.getAutor();
        this.fecha_creacion = datosRegistroRespuestaTopico.getFecha_creacion();

    }

    public void setTopico_id(Topicos topicos) {
        this.topicos = topicos;
    }
}
