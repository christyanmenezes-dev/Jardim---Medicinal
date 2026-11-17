package org.example.backend.exception;

public class FeedbackNotFoundException extends RuntimeException {

    public FeedbackNotFoundException(String mensagem) {
        super(mensagem);
    }
}
