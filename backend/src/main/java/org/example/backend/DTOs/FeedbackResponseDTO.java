package org.example.backend.DTOs;

import lombok.Getter;
import org.example.backend.entity.FeedbackEntity;

@Getter
public class FeedbackResponseDTO {
    private String nome;
    private Integer nota;
    private String avaliacao;

    public FeedbackResponseDTO(FeedbackEntity entity) {
        this.nome = entity.getNome();
        this.nota = entity.getNota();
        this.avaliacao = entity.getAvaliacao();
    }
}
