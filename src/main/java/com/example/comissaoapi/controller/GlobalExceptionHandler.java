package com.example.comissaoapi.controller;

import com.example.comissaoapi.dto.ErroResponse;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgument(IllegalArgumentException exception) {
        return erro(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroResponse> handleBadCredentials() {
        return erro(HttpStatus.UNAUTHORIZED, "E-mail ou senha invalidos.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidation(MethodArgumentNotValidException exception) {
        String mensagem = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Requisicao invalida.");

        return erro(HttpStatus.BAD_REQUEST, mensagem);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErroResponse> handleRestClient(RestClientException exception) {
        return erro(HttpStatus.BAD_GATEWAY, "Erro ao comunicar com a API externa de comissao.");
    }

    private ResponseEntity<ErroResponse> erro(HttpStatus status, String mensagem) {
        ErroResponse response = new ErroResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem
        );
        return ResponseEntity.status(status).body(response);
    }
}
