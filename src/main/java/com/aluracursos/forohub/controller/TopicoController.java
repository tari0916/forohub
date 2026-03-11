package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.topicos.DatosActualizarTopico;
import com.aluracursos.forohub.model.topicos.DatosDetalleTopico;
import com.aluracursos.forohub.model.topicos.DatosListadoTopico;
import com.aluracursos.forohub.model.topicos.DatosRegistroTopico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.aluracursos.forohub.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,UriComponentsBuilder uriComponentsBuilder){
        var topico = service.registrarTopico(datos);
        var uri = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
            var page = service.listarTopicos(paginacion);
            return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallar(@PathVariable Long id){
        var topico = service.detallar(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos){
        var topicoActualizado = service.actualizar(id, datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
