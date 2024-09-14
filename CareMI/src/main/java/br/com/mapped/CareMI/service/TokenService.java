package br.com.mapped.CareMI.service;

import br.com.mapped.CareMI.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${token.password}")
    private String senhaToken;

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(senhaToken);
            return JWT.require(algorithm)
                    .withIssuer("MAPPED INNOVATION - CAREMI").
                    build().
                    verify(token).
                    getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Não foi possível validar o TokenJWT");
        }
    }

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(senhaToken);
            return JWT.create()
                    .withIssuer("MAPPED INNOVATION - CAREMI")
                    .withSubject(usuario.getCpf())
                    .withExpiresAt(Instant.now().plus(Duration.ofMinutes(60)))
                    .sign(algorithm);
        } catch(JWTCreationException e){
            throw new RuntimeException("Não foi possível criar o token JWT");
        }
    }
}
