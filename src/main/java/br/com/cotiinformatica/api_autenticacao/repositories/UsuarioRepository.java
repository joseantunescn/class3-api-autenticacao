package br.com.cotiinformatica.api_autenticacao.repositories;

import br.com.cotiinformatica.api_autenticacao.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // consulta que verifique se um email já existe no banco
    boolean existsByEmail(String email);

    // retorna usuario após consulta por email e senha
    Usuario findByEmailAndSenha(String email, String senha);



}
