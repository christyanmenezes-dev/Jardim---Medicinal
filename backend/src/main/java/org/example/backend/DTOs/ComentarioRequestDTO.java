package org.example.backend.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ComentarioRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 5, max = 300)
    private String comentario;
}
