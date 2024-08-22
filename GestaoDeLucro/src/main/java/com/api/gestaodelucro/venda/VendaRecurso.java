package com.api.gestaodelucro.venda;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@AllArgsConstructor
public class VendaRecurso implements VendaOperations {

    private final VendaServico vendaServico;

    @PatchMapping
    public ResponseEntity<VendaDTO> salvarVenda(@Valid @RequestBody VendaDTO vendaDTO){
        return ResponseEntity.ok().body(vendaServico.atualizarVenda(vendaDTO));
    }

    @GetMapping("/{idPeca}")
    public ResponseEntity<List<VendaDTO>> consultarVendas(@PathVariable Long idPeca ){
        return ResponseEntity.ok().body(vendaServico.consultarVendas(idPeca));
    }
}