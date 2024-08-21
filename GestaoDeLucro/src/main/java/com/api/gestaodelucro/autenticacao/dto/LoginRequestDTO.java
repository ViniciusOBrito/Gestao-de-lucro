package com.api.gestaodelucro.autenticacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Login Request DTO", description = "DTO usado para fazer login")
public record LoginRequestDTO(
        @NotNull(message = "Login e obrigatorio")
        String login,
        @NotNull(message = "Senha e obrigatorio")
        String senha
) {
}
