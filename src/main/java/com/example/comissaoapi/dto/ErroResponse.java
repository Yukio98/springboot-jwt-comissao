package com.example.comissaoapi.dto;

import java.time.Instant;

public record ErroResponse(
        Instant timestamp,
        int status,
        String erro,
        String mensagem
) {
}
