package org.example.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.example.backend.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.Instant;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey chave;

    @PostConstruct
    public void inicializarChave() {
        chave = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String gerarToken(UsuarioEntity usuario) {
        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(3600);

        return Jwts.builder().subject(usuario.getId().toString()).issuedAt(Date.from(agora))
                .expiration(Date.from(expiracao)).signWith(chave).compact();
    }

    public String extrairUsuarioId(String token) {
        return Jwts.parser().verifyWith(chave).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
