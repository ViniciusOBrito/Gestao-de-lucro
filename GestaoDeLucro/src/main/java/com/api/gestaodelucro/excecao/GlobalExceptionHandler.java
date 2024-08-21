package com.api.gestaodelucro.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(GestaoLucroExcecao.class)
    public ProblemDetail handleGestaoLucroException(GestaoLucroExcecao e) {
        return e.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParams(f.getField(), f.getDefaultMessage()));

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Os parametros da requisicao estao incorretos");
        pb.setProperty("campos-invalidos", fieldErros);

        return pb;
    }
    private record InvalidParams(String name, String reason){}
}
