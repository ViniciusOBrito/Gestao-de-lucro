package com.api.gestaodelucro.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class GestaoLucroExcecao extends RuntimeException{

    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Gestao Lucro internal server error");

        return pb;
    }
}
