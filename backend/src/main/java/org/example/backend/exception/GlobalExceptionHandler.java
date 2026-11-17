package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> tratarValidacao(MethodArgumentNotValidException ex) {
        List<String> erros = new ArrayList<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ": " + erro.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> tratarFeedback(FeedbackNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback não encontrado");
    }

    @ExceptionHandler(ComentarioNotFoundException.class)
    public ResponseEntity<String> tratarMensagem(ComentarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado");
    }

    @ExceptionHandler(EmailCadastradoException.class)
    public ResponseEntity<String> tratarEmail(EmailCadastradoException ex) {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> tratarUsuarios(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<String> tratarSenha(CredenciaisInvalidasException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
    }

    @ExceptionHandler(RespostaInvalidaException.class)
    public ResponseEntity<String> tratarResposta(RespostaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cadastro inválido");
    }
}
