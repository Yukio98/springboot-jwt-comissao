package com.example.comissaoapi.dto;

public record AuthResponse(
        String token,
        String tipo,
        UsuarioResponse usuario
) {
}
