package br.com.cotiinformatica.api_autenticacao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column(length = 30, nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;
}
