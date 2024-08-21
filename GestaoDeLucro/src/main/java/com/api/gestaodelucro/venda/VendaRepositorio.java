package com.api.gestaodelucro.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long> {

    Venda findByIdPecaAndIdProdutoOrderByDataAtualizacaoDesc(Long idPeca, Long idProduto);
}
