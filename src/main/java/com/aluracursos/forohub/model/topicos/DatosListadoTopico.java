package com.aluracursos.forohub.model.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estadoDelTopico,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstadoDelTopico(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
