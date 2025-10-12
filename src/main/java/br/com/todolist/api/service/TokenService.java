package br.com.todolist.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.todolist.api.domain.User.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("Tasks Api")
                .withSubject(user.getUsername())
                .withExpiresAt(DtExpiration())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro na criacao do token: ", exception);
        }
    }


    public String getSubject(String token){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Tasks Api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("token invalido ou expirado");
        }
    }

    public Instant DtExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
