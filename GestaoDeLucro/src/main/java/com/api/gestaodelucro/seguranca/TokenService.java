package com.api.gestaodelucro.seguranca;

import com.api.gestaodelucro.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secret);
    }

    public String criarToken(Usuario usuario){
        try{
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarTempoExpiracao())
                    .sign(getAlgorithm());
        }catch (JWTCreationException e){
            throw new  RuntimeException("Erro na criacao do TOKEN", e);
        }
    }

    public String validarToken(String subject){
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(subject)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }

    public Instant buscaTempoExpiracaoDoToken(String token){
        return JWT.require(getAlgorithm())
                .withIssuer("login-auth-api")
                .build()
                .verify(token)
                .getExpiresAt()
                .toInstant();
    }

    public Instant gerarTempoExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }


}
