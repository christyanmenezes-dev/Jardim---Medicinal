package org.example.backend.DTOs;

import lombok.Getter;
import org.example.backend.entity.ComentarioEntity;

@Getter
public class ComentarioResponseDTO {
    private String nome;
    private String comentario;
    private Long id;

    public ComentarioResponseDTO(ComentarioEntity entity) {
        this.nome = entity.getNome();
        this.id = entity.getId();
        this.comentario = entity.getComentario();
    }
}
