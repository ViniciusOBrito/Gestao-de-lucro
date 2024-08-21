package com.api.gestaodelucro.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EntidadeNaoEncontradaExcecao extends GestaoLucroExcecao{

    private final String detalhe;

    public EntidadeNaoEncontradaExcecao(String detalhe) {
        this.detalhe = detalhe;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Entidade nao encontrada");
        pb.setDetail(detalhe);

        return pb;
    }
}
