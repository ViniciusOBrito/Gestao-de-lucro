package com.api.gestaodelucro.peca;

import com.api.gestaodelucro.calculo.CalculoServico;
import com.api.gestaodelucro.excecao.EntidadeNaoEncontradaExcecao;
import com.api.gestaodelucro.produto.Produto;
import com.api.gestaodelucro.produto.ProdutoServico;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PecaServico {

    private final PecaRepositorio pecaRepositorio;
    private final CalculoServico calculoServico;
    private final ProdutoServico produtoServico;

    @Transactional
    public PecaDTO cadastrarPeca(PecaDTO pecaDTO){

        Peca peca = new Peca();
        BeanUtils.copyProperties(pecaDTO, peca);

        List<Produto> produtos = pecaDTO.listaDeProdutos().stream().map(
                produtoDTO -> {
                    Produto produto = new Produto();
                    BeanUtils.copyProperties(produtoDTO, produto);
                    calculoServico.calcularValoresDoProduto(produto, pecaDTO);

                    return produto;
                }).toList();

        produtos.add(produtoServico.getProdutoDesossa(pecaDTO));

        produtos.forEach(peca::addProduto);

        return salvarPeca(peca);
    }

    public PecaDTO buscarPeca(Long id){
        Peca peca =  pecaRepositorio.findById(id).
                orElseThrow(()-> new EntidadeNaoEncontradaExcecao(String.format("Não foi encontado nenhum registro com o ID: %d", id)));
        return new PecaDTO(peca);
    }

    public List<PecaDTO> listarPecas(Long idPeca){
        return pecaRepositorio.findAllByIdPeca(idPeca)
                .stream()
                .map(PecaDTO::new)
                .toList();
    }

    public PecaDTO salvarPeca(Peca peca){
        Peca pecaSalva =  pecaRepositorio.save(peca);
        PecaDTO pecaDTO = new PecaDTO();
        BeanUtils.copyProperties(pecaSalva, pecaDTO);

        return pecaDTO;
    }
}
