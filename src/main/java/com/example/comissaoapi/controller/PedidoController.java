package com.example.comissaoapi.controller;

import com.example.comissaoapi.dto.ComissaoResponse;
import com.example.comissaoapi.dto.PedidoRequest;
import com.example.comissaoapi.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<ComissaoResponse> lancarPedido(@RequestBody @Valid PedidoRequest request) {
        return ResponseEntity.ok(pedidoService.lancarPedido(request));
    }
}
