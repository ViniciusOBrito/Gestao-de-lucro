package com.api.gestaodelucro.venda;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record VendaDTO(
        Long id,
        @NotNull(message = "Id do produto não pode ser nulo.") Long idProduto,
        @NotNull(message = "Id da peça não pode ser nulo.") Long idPeca,
        @NotNull(message = "Valor de venda não pode ser nulo.") BigDecimal valorDeVenda,
        Date dataAtualizacao
) {
}
