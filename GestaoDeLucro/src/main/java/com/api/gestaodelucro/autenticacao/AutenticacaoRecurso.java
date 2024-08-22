package com.api.gestaodelucro.autenticacao;

import com.api.gestaodelucro.autenticacao.dto.LoginRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.LoginResponseDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroRequestDTO;
import com.api.gestaodelucro.autenticacao.dto.RegistroResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@AllArgsConstructor
public class AutenticacaoRecurso implements AutenticacaoOperations{

    private final AutenticacaoServico autenticacaoServico;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(autenticacaoServico.login(loginRequestDTO));
    }

    @PostMapping("/registrar")
    public ResponseEntity<RegistroResponseDTO> registrar(@Valid @RequestBody RegistroRequestDTO dto){
        return ResponseEntity.ok(autenticacaoServico.registraUsuario(dto));
    }
}
