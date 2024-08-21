package com.api.gestaodelucro.venda;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class VendaServico {

    private final VendaRepositorio vendaRepositorio;

    public VendaServico(VendaRepositorio vendaRepositorio) {
        this.vendaRepositorio = vendaRepositorio;
    }

    public VendaDTO salvarVenda(VendaDTO vendaDTO){

        Venda venda = new Venda();
        BeanUtils.copyProperties(vendaDTO, venda);
        venda.setDataAtualizacao(new Date());

        venda =  vendaRepositorio.save(venda);
        BeanUtils.copyProperties(venda, vendaDTO);
        return vendaDTO;
    }

    public BigDecimal buscaVendaPorIdProdutoEPeca(Long idPeca, Long idProduto){
        return vendaRepositorio.findByIdPecaAndIdProdutoOrderByDataAtualizacaoDesc(idPeca, idProduto).getValorDeVenda();
    }
}