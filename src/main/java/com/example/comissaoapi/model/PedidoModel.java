package com.example.comissaoapi.model;

import java.math.BigDecimal;

public record PedidoModel(
        String codigoPedido,
        BigDecimal valorPedido,
        String vendedorEmail
) {
}
