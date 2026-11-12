package br.com.cotiinformatica.api_autenticacao.handlers;

import br.com.cotiinformatica.api_autenticacao.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_autenticacao.exceptions.EmailJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    /*
        Método para tratamento global dos erros de validação
        gerados pelo Bean Validation em DTOs recebidos com @Valid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(
            MethodArgumentNotValidException exception) {

        // Agrupa as mensagens de validação pelo nome do campo.
        Map<String, List<String>> erros = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(erro -> {
            erros.computeIfAbsent(
                    erro.getField(),
                    campo -> new ArrayList<>()
            ).add(erro.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros);
    }


    /*
    Método para tratamento global dos erros de acesso negado
 */
    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<Map<String, String>> handleEmailJaCadastradoException(
            AcessoNegadoException exception) {

        // Agrupa as mensagens de validação pelo nome do campo.
        Map<String, String> erros = new LinkedHashMap<>();

        erros.put("erro", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(erros);
    }

        /*
    Método para tratamento global dos erros de email já cadastrado
    */
        @ExceptionHandler(EmailJaCadastradoException.class)
        public ResponseEntity<Map<String, String>> handleEmailJaCadastradoException(
                EmailJaCadastradoException exception) {

            // Agrupa as mensagens de validação pelo nome do campo.
            Map<String, String> erros = new LinkedHashMap<>();

            erros.put("email", exception.getMessage());

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(erros);
        }

}