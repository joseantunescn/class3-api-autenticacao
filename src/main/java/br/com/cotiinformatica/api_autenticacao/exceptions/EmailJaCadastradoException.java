package br.com.cotiinformatica.api_autenticacao.exceptions;

public class EmailJaCadastradoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Email informado já cadastrado. Tente outro.";
    }
}
