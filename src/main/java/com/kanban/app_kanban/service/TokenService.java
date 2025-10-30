package com.kanban.app_kanban.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kanban.app_kanban.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String tokenGeneration(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("kaban-app")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generateExpDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e ){
            throw new RuntimeException("Erro na criação do token" + e.getMessage());
        }
    }

    private Instant generateExpDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            String valid = JWT.require(algorithm)
                    .withIssuer("kanban-app")
                    .build()
                    .verify(token)
                    .getSubject();
            return valid;
        }catch (JWTVerificationException e ){
            return "";
        }

    }
}
