package com.api.gestaodelucro.autenticacao.dto;

public record RegistroResponseDTO(
        String login, String token, String expiraEm
) {
}
