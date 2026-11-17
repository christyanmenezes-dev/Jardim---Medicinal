package org.example.backend.DTOs;

import lombok.Getter;
import org.example.backend.entity.RespostaComentariosEntity;

@Getter
public class RespostaComentariosResponseDTO {
    private String nome;
    private String resposta;
    private Long id;
    private Long comentarioId;

    public RespostaComentariosResponseDTO(RespostaComentariosEntity entity) {
        this.nome = entity.getNome();
        this.resposta = entity.getResposta();
        this.id = entity.getId();
        this.comentarioId = entity.getComentario().getId();
    }
}
