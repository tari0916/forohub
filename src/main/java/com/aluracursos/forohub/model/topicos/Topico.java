package com.aluracursos.forohub.model.topicos;

import com.aluracursos.forohub.model.cursos.Curso;
import com.aluracursos.forohub.model.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estadoDelTopico;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.estadoDelTopico = Estado.ABIERTO;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datos){
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
    }

}
