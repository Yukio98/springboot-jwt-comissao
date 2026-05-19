package com.example.comissaoapi.service;

import com.example.comissaoapi.dto.ComissaoResponse;
import com.example.comissaoapi.dto.PedidoRequest;
import com.example.comissaoapi.model.ComissaoModel;
import com.example.comissaoapi.model.PedidoModel;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final ComissaoApiClient comissaoApiClient;

    public PedidoService(ComissaoApiClient comissaoApiClient) {
        this.comissaoApiClient = comissaoApiClient;
    }

    public ComissaoResponse lancarPedido(PedidoRequest request) {
        PedidoModel pedido = new PedidoModel(
                request.codigoPedido(),
                request.valorPedido(),
                request.vendedorEmail()
        );

        ComissaoModel comissao = comissaoApiClient.lancarPedido(pedido);

        return new ComissaoResponse(
                comissao.codigoPedido(),
                comissao.valorPedido(),
                comissao.valorComissao(),
                comissao.status()
        );
    }
}
