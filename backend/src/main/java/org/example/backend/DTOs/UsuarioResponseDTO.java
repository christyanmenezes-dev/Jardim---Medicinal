package org.example.backend.DTOs;

import lombok.Getter;
import org.example.backend.entity.UsuarioEntity;

@Getter
public class UsuarioResponseDTO {

    private String nome;
    private String email;
    private Long id;

    public UsuarioResponseDTO(UsuarioEntity entity) {
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.id = entity.getId();
    }
}
