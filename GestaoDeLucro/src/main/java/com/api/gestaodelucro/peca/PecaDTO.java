package com.api.gestaodelucro.peca;

import com.api.gestaodelucro.produto.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record PecaDTO(
        Long id,
        @NotNull(message = "ID da peça não pode ser nulo.") Long idPeca,
        @NotNull(message = "O peso da peça não pode ser nulo.") BigDecimal peso,
        @NotNull(message = "O valor da compra não pode ser nulo.") BigDecimal valorDeCompra,
        @Size(min = 1, message = "A lista de produtos não pode estar vazia.") List<Produto> listaDeProdutos
) {
    public PecaDTO(Peca peca) {
        this(
                peca.getId(),
                peca.getIdPeca(),
                peca.getPeso(),
                peca.getValorDeCompra(),
                peca.getListaDeProdutos()
        );
    }

    public PecaDTO() {
        this(
                null,
                null,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                List.of()
        );
    }
}
