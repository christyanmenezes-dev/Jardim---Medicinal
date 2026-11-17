package org.example.backend.exception;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super(message);
    }
}
