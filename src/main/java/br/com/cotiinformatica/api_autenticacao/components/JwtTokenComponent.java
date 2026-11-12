package br.com.cotiinformatica.api_autenticacao.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenComponent {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Retorna a data e hora de expiração em UTC
    public LocalDateTime getExpiration() {
        return LocalDateTime.ofInstant(
                Instant.now().plusMillis(expiration),
                ZoneOffset.UTC
        );
    }

    // Gera o token JWT
    public String getToken(String username, String role) {

        Instant agora = Instant.now();
        Instant dataExpiracao = agora.plusMillis(expiration);

        return Jwts.builder()
                .setSubject(username) //Usuário que gerou o TOKEN JWT
                .claim("role", role) //Perfil do usuário
                .setIssuedAt(Date.from(agora)) //Data e hora de geração do TOKEN
                .setExpiration(Date.from(dataExpiracao)) //Data de exíração do token
                .signWith(SignatureAlgorithm.HS256, secret) //Chave secreta para criptografia
                .compact(); //Retornar o TOKEN

    }
}