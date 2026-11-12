package br.com.cotiinformatica.api_autenticacao.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record CriarUsuarioResponse(

        String mensagem,
        UUID id,
        String nome,
        String email,
        String perfil,
        LocalDateTime dataCriacao


) {
}
