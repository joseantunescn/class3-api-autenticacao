package br.com.cotiinformatica.api_autenticacao.repositories;

import br.com.cotiinformatica.api_autenticacao.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {

    Perfil findByNome(String nome);

}
