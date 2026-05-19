package com.example.comissaoapi.service;

import com.example.comissaoapi.model.ComissaoModel;
import com.example.comissaoapi.model.PedidoModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ComissaoApiClient {

    private final RestClient restClient;
    private final String pedidoPath;

    public ComissaoApiClient(
            RestClient.Builder restClientBuilder,
            @Value("${app.commission-api.base-url}") String baseUrl,
            @Value("${app.commission-api.pedido-path}") String pedidoPath
    ) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
        this.pedidoPath = pedidoPath;
    }

    public ComissaoModel lancarPedido(PedidoModel pedido) {
        return restClient.post()
                .uri(pedidoPath)
                .body(pedido)
                .retrieve()
                .body(ComissaoModel.class);
    }
}
