package br.com.cotiinformatica.api_autenticacao.exceptions;

public class AcessoNegadoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Acesso negado. Usuario inválido.";
    }
}
