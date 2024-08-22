package com.api.gestaodelucro.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Schema(name = "Venda DTO", description = "DTO usado para cadastrar um novo valor de venda de um produto")
public record VendaDTO(
        @NotNull(message = "Id do produto não pode ser nulo.") Long idProduto,
        @NotNull(message = "Id da peça não pode ser nulo.") Long idPeca,
        @NotNull(message = "Valor de venda não pode ser nulo.") BigDecimal valorDeVenda,
        Date dataAtualizacao
) {

    public VendaDTO(Venda venda){
        this(
                venda.getIdProduto(),
                venda.getIdPeca(),
                venda.getValorDeVenda(),
                venda.getDataAtualizacao()
        );
    }
}
