package org.example.backend.DTOs;

import lombok.Getter;
import org.example.backend.entity.UsuarioEntity;

@Getter
public class LoginResponseDTO {

    private String token;
    private Long id;
    private String nome;
    private String email;

    public LoginResponseDTO(UsuarioEntity entity, String token) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.token = token;
    }
}
