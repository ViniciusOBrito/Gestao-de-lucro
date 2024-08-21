package com.api.gestaodelucro.peca;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
@Tag(name = "Peças", description = "Operações relacionadas as peças")
public interface PecaOperations {

    @Operation(
            summary = "Cadastra uma peça",
            description = "Recebe um DTO contendo informações da Peça e dos produtos que contém nela.")
    ResponseEntity<PecaDTO> cadastrar(PecaDTO pecaDTO);

    @Operation(
            summary = "Busca uma peça",
            description = "Busca uma peca pelo Id")
    ResponseEntity<PecaDTO> buscaPeca(Long id);

    @Operation(
            summary = "Busca todas as pecas de um idPeca",
            description = "Lista todas as pecas de um ID de peca")
    ResponseEntity<List<PecaDTO>> listarPecas(Long idPeca);
}
