package com.example.comissaoapi.service;

import com.example.comissaoapi.dto.AuthResponse;
import com.example.comissaoapi.dto.LoginRequest;
import com.example.comissaoapi.dto.UsuarioResponse;
import com.example.comissaoapi.entity.Usuario;
import com.example.comissaoapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService,
            UsuarioService usuarioService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    public AuthResponse autenticar(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        Usuario usuario = usuarioService.buscarPorEmail(request.email());
        String token = jwtService.gerarToken(userDetails);

        return new AuthResponse(token, "Bearer", UsuarioResponse.fromEntity(usuario));
    }
}
