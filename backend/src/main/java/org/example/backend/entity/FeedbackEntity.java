package org.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FeedbackEntity {
    private String nome;
    private Integer nota;
    private String avaliacao;
    @Id
    @GeneratedValue
    private Long id;
}
