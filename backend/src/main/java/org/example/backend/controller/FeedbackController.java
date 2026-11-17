package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTOs.FeedbackRequestDTO;
import org.example.backend.DTOs.FeedbackResponseDTO;
import org.example.backend.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService service;

    @GetMapping
    public List<FeedbackResponseDTO> procurarTodos(){return service.procurarTodos();}

    @GetMapping("/{id}")
    public FeedbackResponseDTO procurarId(@PathVariable Long id) {
        return service.procurarId(id);
    }

    @PostMapping
    public FeedbackResponseDTO criarFeedback(@Valid @RequestBody FeedbackRequestDTO feedback) {
        return service.criarFeedback(feedback);
    }

    @PutMapping("/{id}")
    public FeedbackResponseDTO alterarFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackRequestDTO feedback) {
        return service.alterarFeedback(id, feedback);
    }

    @DeleteMapping("/{id}")
    public void deletarFeedback(@PathVariable Long id) {
        service.deletarFeedback(id);
    }
}
