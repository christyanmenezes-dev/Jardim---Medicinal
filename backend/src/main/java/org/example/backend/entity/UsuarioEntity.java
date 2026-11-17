package org.example.backend.entity;

import jakarta.persistence.Column;
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
public class UsuarioEntity {

    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;

    @Id
    @GeneratedValue
    private Long id;
}
