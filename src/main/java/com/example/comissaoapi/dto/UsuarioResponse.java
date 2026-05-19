package com.example.comissaoapi.dto;

import com.example.comissaoapi.entity.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Boolean salvo
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSalvo()
        );
    }
}
