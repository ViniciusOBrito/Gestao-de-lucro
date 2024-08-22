package com.api.gestaodelucro.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long> {

    Optional<Venda> findByIdPecaAndIdProduto(Long idPeca, Long idProduto);

    List<Venda> findAllByIdPeca(Long idPeca);
}
