package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTOs.UsuarioRequestDTO;
import org.example.backend.DTOs.UsuarioResponseDTO;
import org.example.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public UsuarioResponseDTO cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        return service.cadastrarUsuario(dto);
    }

    @GetMapping("{id}")
    public UsuarioResponseDTO procurarId(@PathVariable Long id) {
        return service.procurarId(id);
    }

}
