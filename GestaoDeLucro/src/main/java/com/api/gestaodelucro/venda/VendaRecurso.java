package com.api.gestaodelucro.venda;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
@AllArgsConstructor
public class VendaRecurso implements VendaOperations {

    private final VendaServico vendaServico;

    @PostMapping
    public ResponseEntity<VendaDTO> salvarVenda(@Valid @RequestBody VendaDTO vendaDTO){
        return ResponseEntity.ok().body(vendaServico.salvarVenda(vendaDTO));
    }
}