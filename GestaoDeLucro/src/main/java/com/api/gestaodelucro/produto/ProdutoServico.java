package com.api.gestaodelucro.produto;

import com.api.gestaodelucro.calculo.CalculoServico;
import com.api.gestaodelucro.peca.PecaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoServico {

    private final CalculoServico calculoServico;

    public Produto getProdutoDesossa(PecaDTO pecaDTO){

        Produto produto = new Produto();
        produto.setIdProduto(9L);
        produto.setQuantidade(calculoServico.calcularPercaDesossa(
                pecaDTO.listaDeProdutos(), pecaDTO.peso()
        ));

        return produto;
    }
}
