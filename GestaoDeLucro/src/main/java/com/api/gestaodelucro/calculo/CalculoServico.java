package com.api.gestaodelucro.calculo;

import com.api.gestaodelucro.peca.PecaDTO;
import com.api.gestaodelucro.produto.Produto;
import com.api.gestaodelucro.venda.VendaServico;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class CalculoServico {

    private final VendaServico vendaServico;

    public void calcularValoresDoProduto(Produto produto, PecaDTO pecaDTO){
        BigDecimal percentual = this.calculaPercentual(produto.getQuantidade(), pecaDTO.peso());
        BigDecimal custo = this.calculaCustoProduto(pecaDTO.listaDeProdutos(), pecaDTO.peso());
        BigDecimal venda = vendaServico.buscaVendaPorIdProdutoEPeca(pecaDTO.idPeca(), produto.getIdProduto());
        BigDecimal total = this.calculaVendaTotalProduto(venda, produto.getQuantidade());
        produto.setPercentual(percentual);
        produto.setCusto(custo);
        produto.setVenda(venda);
        produto.setTotal(total);
    }

    public BigDecimal calculaVendaTotalProduto(BigDecimal valorVenda, BigDecimal quantidade){
        return valorVenda.multiply(quantidade);
    }

    public BigDecimal calculaCustoProduto(List<Produto> listaDeProdutos, BigDecimal precoDianteiro){
        BigDecimal somaDoPesoDosProdutos = this.somaPesoDosProdutos(listaDeProdutos);

        return precoDianteiro.divide(somaDoPesoDosProdutos, RoundingMode.HALF_UP);
    }

    public BigDecimal calculaPercentual(BigDecimal pesoProduto, BigDecimal pesoDianteiro){
        BigDecimal valorDividido = pesoProduto.divide(pesoDianteiro, 3, RoundingMode.HALF_UP);
        return valorDividido.multiply(BigDecimal.valueOf(100)).setScale(RoundingMode.HALF_UP.ordinal());
    }

    public BigDecimal calcularPercaDesossa(List<Produto> listaDeProdutos, BigDecimal pesoDianteiro){
        BigDecimal somaDoPesoDosProdutos = this.somaPesoDosProdutos(listaDeProdutos);
        return pesoDianteiro.subtract(somaDoPesoDosProdutos);
    }

    public BigDecimal somaPesoDosProdutos(List<Produto> listaDeProdutos){
        return listaDeProdutos.
                stream().
                map(Produto::getQuantidade)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
