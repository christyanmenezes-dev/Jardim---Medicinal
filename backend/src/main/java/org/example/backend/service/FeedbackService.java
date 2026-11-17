package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.DTOs.FeedbackRequestDTO;
import org.example.backend.DTOs.FeedbackResponseDTO;
import org.example.backend.entity.FeedbackEntity;
import org.example.backend.exception.FeedbackNotFoundException;
import org.example.backend.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository repository;

    public FeedbackResponseDTO criarFeedback(FeedbackRequestDTO dto) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setNome(dto.getNome());
        feedback.setNota(dto.getNota());
        feedback.setAvaliacao(dto.getAvaliacao());

        FeedbackEntity feedbackSalvo = repository.save(feedback);
        FeedbackResponseDTO enviarFeedback = new FeedbackResponseDTO(feedbackSalvo);
        return enviarFeedback;
    }

    public FeedbackResponseDTO procurarId(Long id) {
        Optional<FeedbackEntity> procurarPorId = repository.findById(id);
        if (procurarPorId.isPresent()) {
            FeedbackEntity resultado = procurarPorId.get();
            FeedbackResponseDTO converter = new FeedbackResponseDTO(resultado);
            return converter;
        } else {
            throw new FeedbackNotFoundException("Feedback não encontrado");
        }
    }

    public List<FeedbackResponseDTO> procurarTodos() {
        List<FeedbackEntity> procurarFeedback = repository.findAll();

        List<FeedbackResponseDTO> feedback = new ArrayList<>();
        for (FeedbackEntity feedbacks : procurarFeedback) {
            feedback.add(new FeedbackResponseDTO(feedbacks));
        }

        return feedback;
    }

    public FeedbackResponseDTO alterarFeedback(Long id, FeedbackRequestDTO feedback) {
        Optional<FeedbackEntity> procura = repository.findById(id);
        if (procura.isPresent()) {
            FeedbackEntity resultado = procura.get();
            resultado.setNome(feedback.getNome());
            resultado.setNota(feedback.getNota());
            resultado.setAvaliacao(feedback.getAvaliacao());

            FeedbackEntity salvar = repository.save(resultado);

            FeedbackResponseDTO converter = new FeedbackResponseDTO(salvar);

            return converter;
        } else {
            throw new FeedbackNotFoundException("Feedback não encontrado");
        }

    }
    public void deletarFeedback(Long id) {
        Optional<FeedbackEntity> procura = repository.findById(id);
        if (procura.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new FeedbackNotFoundException("Feedback não encontrado");
        }
    }
}
