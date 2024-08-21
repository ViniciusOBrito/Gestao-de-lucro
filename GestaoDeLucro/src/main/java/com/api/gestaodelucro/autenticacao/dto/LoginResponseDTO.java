package com.api.gestaodelucro.autenticacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Registro Request DTO", description = "DTO usado para o registro de usuario")
public record LoginResponseDTO(String login, String token, String expiraEm
) {
}
