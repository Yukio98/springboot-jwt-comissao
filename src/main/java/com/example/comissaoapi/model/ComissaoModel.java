package com.example.comissaoapi.model;

import java.math.BigDecimal;

public record ComissaoModel(
        String codigoPedido,
        BigDecimal valorPedido,
        BigDecimal valorComissao,
        String status
) {
}
