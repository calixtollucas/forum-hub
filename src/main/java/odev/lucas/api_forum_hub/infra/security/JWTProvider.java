package odev.lucas.api_forum_hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTProvider {

    @Value("${api.security.jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api-forum-hub")
                    .withExpiresAt(gerarExpiracao())
                    .withSubject(usuario.getEmail().toString())
                    .sign(alg);
        } catch (JWTCreationException ex) {
            throw new DomainException("Erro ao criar o token JWT, contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("api-forum-hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new DomainException("Token inválida ou expirada", HttpStatus.UNAUTHORIZED);
        }
    }

    private Instant gerarExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
