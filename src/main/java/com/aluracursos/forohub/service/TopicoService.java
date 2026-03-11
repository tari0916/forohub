package com.aluracursos.forohub.service;

import com.aluracursos.forohub.model.cursos.Curso;
import com.aluracursos.forohub.model.cursos.CursoRepository;
import com.aluracursos.forohub.model.topicos.*;
import com.aluracursos.forohub.model.usuarios.Usuario;
import com.aluracursos.forohub.model.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Topico registrarTopico(DatosRegistroTopico datos) {

        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario usuario = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow();

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow();

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                null,
                Estado.ABIERTO,
                usuario,
                curso
        );
        return topicoRepository.save(topico);
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    public Topico detallar(Long id){
        return topicoRepository.getReferenceById(id);
    }

    @Transactional
    public DatosDetalleTopico actualizar(Long id, DatosActualizarTopico datos) {
        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }
        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datos);
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public void eliminar(Long id){
        var optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()){
            topicoRepository.deleteById(id);
        }else{
            throw new RuntimeException("Topico no encontrado");
        }
    }
}
