package com.api.gestaodelucro.autenticacao.dto;

import com.api.gestaodelucro.usuario.Cargo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Schema(name = "Registro Request DTO", description = "DTO usado para o registro de usuario")
public record RegistroRequestDTO(

        @NotBlank(message = "O login e obrigatorio")
        String login,
        @NotNull(message = "A senha e obrigatoria")
        String senha,
        @NotNull(message = "O cargo e obrigatorio")
        Cargo cargo
) {
}
