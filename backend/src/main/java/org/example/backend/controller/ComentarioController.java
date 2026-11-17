package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTOs.ComentarioRequestDTO;
import org.example.backend.DTOs.ComentarioResponseDTO;
import org.example.backend.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@AllArgsConstructor
public class ComentarioController {

    private final ComentarioService service;

    @GetMapping
    public List<ComentarioResponseDTO> procurarTodos() {
        return service.procurarTodos();
    }

    @PostMapping
    public ComentarioResponseDTO criarComentario(@Valid @RequestBody ComentarioRequestDTO comentario) {
        return service.criarComentario(comentario);
    }

    @PutMapping("{id}")
    public ComentarioResponseDTO alterarComentario(@PathVariable Long id, ComentarioRequestDTO comentario) {
        return service.alterarComentario(id, comentario);
    }

    @DeleteMapping("{id}")
    public void deletarComentario(@PathVariable Long id) {
        service.deletarComentario(id);
    }
}
