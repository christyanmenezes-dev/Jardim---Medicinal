package org.example.backend.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FeedbackRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min=5, max=300)
    private String avaliacao;
    @NotNull
    @Min(0)
    @Max(5)
    private Integer nota;
}
