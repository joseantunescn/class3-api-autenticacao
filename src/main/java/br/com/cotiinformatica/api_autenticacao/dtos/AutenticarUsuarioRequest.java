package br.com.cotiinformatica.api_autenticacao.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AutenticarUsuarioRequest(

        @NotEmpty(message = "Campo obrigatório")
        @Email
        String email,

        @NotEmpty(message = "Campo obrigatório")
        @Pattern(

                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9\\s]).{8,}$",
                message = "A senha deve ser forte"
        )
        String senha

) {
}
