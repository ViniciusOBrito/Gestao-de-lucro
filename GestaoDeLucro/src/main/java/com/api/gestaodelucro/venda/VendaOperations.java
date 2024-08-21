package com.api.gestaodelucro.venda;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Venda", description = "Operacoes relacionada as vendas")
public interface VendaOperations {

    @Operation(
            summary = "Cadastra uma venda",
            description = "Recebe um DTO contendo informacoes da venda para serem cadastradas."
    )
    ResponseEntity<VendaDTO> salvarVenda(VendaDTO vendaDTO);
}
