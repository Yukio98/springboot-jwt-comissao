package com.example.comissaoapi.controller;

import com.example.comissaoapi.dto.AuthResponse;
import com.example.comissaoapi.dto.LoginRequest;
import com.example.comissaoapi.service.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    private final AuthService authService;

    public PublicController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public Map<String, String> inicio() {
        return Map.of(
                "status", "Comissao API rodando",
                "login", "Envie POST /login ou POST /auth/login com email e senha"
        );
    }

    @GetMapping("/login")
    public Map<String, String> loginInfo() {
        return Map.of(
                "erro", "Login deve ser feito com POST, nao GET",
                "exemplo", "{\"email\":\"lucas@email.com\",\"senha\":\"123456\"}"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.autenticar(request));
    }
}
