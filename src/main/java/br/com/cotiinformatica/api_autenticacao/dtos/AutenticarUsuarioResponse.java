package br.com.cotiinformatica.api_autenticacao.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AutenticarUsuarioResponse(

        String mensagem,
        UUID id,
        String nome,
        String email,
        String perfil,
        LocalDateTime dataHoraAcesso,
        LocalDateTime dataHoraExpiracao,
        String accessToken

) {
}
