package com.api.gestaodelucro.venda;

import com.api.gestaodelucro.excecao.EntidadeNaoEncontradaExcecao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class VendaServico {

    private final VendaRepositorio vendaRepositorio;

    public VendaServico(VendaRepositorio vendaRepositorio) {
        this.vendaRepositorio = vendaRepositorio;
    }

    public VendaDTO atualizarVenda(VendaDTO vendaDTO){

        Venda venda = vendaRepositorio.findByIdPecaAndIdProduto(vendaDTO.idPeca(), vendaDTO.idProduto())
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Venda nao encontrada."));

        BeanUtils.copyProperties(vendaDTO, venda);
        venda.setDataAtualizacao(new Date());

        venda =  vendaRepositorio.save(venda);
        BeanUtils.copyProperties(venda, vendaDTO);
        return vendaDTO;
    }

    public BigDecimal buscaValorVendaPorIdProdutoEPeca(Long idPeca, Long idProduto){
        Venda  venda = vendaRepositorio.findByIdPecaAndIdProduto(idPeca, idProduto)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao("Venda nao encontrada."));

        return venda.getValorDeVenda();
    }

    public List<VendaDTO> consultarVendas(Long idPeca){
        return vendaRepositorio.findAllByIdPeca(idPeca)
                .stream()
                .map(VendaDTO::new)
                .toList();
    }
}