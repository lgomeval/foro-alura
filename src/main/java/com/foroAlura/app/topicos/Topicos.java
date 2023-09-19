package com.foroAlura.app.topicos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private String fecha_creacion;
    private String estatus;
    private String autor;
    private String curso;

    // Contructor

    public Topicos(DatosRegistroTopico datosRegistroTopico) {

        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
        this.estatus = datosRegistroTopico.estatus();
        this.fecha_creacion = datosRegistroTopico.fecha_creacion();
    }

    public Topicos(DatosActualizarTopico datosActualizarTopico) {

        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.autor = datosActualizarTopico.autor();
        this.curso = datosActualizarTopico.curso();
        this.estatus = datosActualizarTopico.estatus();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
