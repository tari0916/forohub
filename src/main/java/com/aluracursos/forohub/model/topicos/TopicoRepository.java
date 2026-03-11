package com.aluracursos.forohub.model.topicos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    boolean existsByTituloAndMensaje(String titulo,String mensaje);
}
