package com.example.comissaoapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PedidoRequest(
        @NotBlank String codigoPedido,
        @NotNull @DecimalMin("0.01") BigDecimal valorPedido,
        @NotBlank String vendedorEmail
) {
}
