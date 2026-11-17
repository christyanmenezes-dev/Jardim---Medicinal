package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.LoginRequestDTO;
import org.example.backend.DTOs.LoginResponseDTO;
import org.example.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService service;

    @PostMapping("/login")
    public LoginResponseDTO fazerLogin(@Valid @RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }
}
