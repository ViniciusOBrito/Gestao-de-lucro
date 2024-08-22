package com.api.gestaodelucro.autenticacao;

import com.api.gestaodelucro.autenticacao.dto.LoginRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.LoginResponseDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticacao",  description = "Operacoes relacionadas a autenticacao")
public interface AutenticacaoOperations {


    @Operation(
            summary = "Realizar login",
            description = "Recebe um DTO para realizar o login do usuario."
    )
    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);


    @Operation(
            summary = "Registrar usuario",
            description = "Recebe um DTO para poder registrar um usuario."
    )
    ResponseEntity<RegistroResponseDTO> registrar(RegistroRequestDTO registroRequestDTO);
}
