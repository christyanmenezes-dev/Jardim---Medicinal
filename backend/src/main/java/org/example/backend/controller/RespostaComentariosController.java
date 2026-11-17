package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTOs.RespostaComentariosRequestDTO;
import org.example.backend.DTOs.RespostaComentariosResponseDTO;
import org.example.backend.service.RespostaComentariosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respostas")
@AllArgsConstructor
public class RespostaComentariosController {

    private final RespostaComentariosService service;

    @PostMapping
    public RespostaComentariosResponseDTO criarResposta(@Valid @RequestBody RespostaComentariosRequestDTO resposta) {
        return service.criarResposta(resposta);
    }

    @GetMapping
    public List<RespostaComentariosResponseDTO> procurarTodos() {return service.procurarTodos();}

    @PutMapping("{id}")
    public RespostaComentariosResponseDTO alterarResposta(@PathVariable Long id, @Valid @RequestBody RespostaComentariosRequestDTO resposta) {
        return service.alterarResposta(id, resposta);
    }

    @DeleteMapping("{id}")
    public void deletarResposta(@PathVariable Long id) {
        service.deletarResposta(id);
    }
}
