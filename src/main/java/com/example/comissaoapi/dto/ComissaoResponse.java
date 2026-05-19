package com.example.comissaoapi.dto;

import java.math.BigDecimal;

public record ComissaoResponse(
        String codigoPedido,
        BigDecimal valorPedido,
        BigDecimal valorComissao,
        String status
) {
}
