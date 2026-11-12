package br.com.cotiinformatica.api_autenticacao.services;

import br.com.cotiinformatica.api_autenticacao.components.CryptoComponent;
import br.com.cotiinformatica.api_autenticacao.components.JwtTokenComponent;
import br.com.cotiinformatica.api_autenticacao.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.api_autenticacao.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_autenticacao.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_autenticacao.entities.Perfil;
import br.com.cotiinformatica.api_autenticacao.entities.Usuario;
import br.com.cotiinformatica.api_autenticacao.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_autenticacao.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_autenticacao.repositories.PerfilRepository;
import br.com.cotiinformatica.api_autenticacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        if(usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException();
        }

        var perfil = perfilRepository.findByNome("Operador");
        if(perfil == null) {
            perfil = new Perfil();
            perfil.setNome("Operador");

            perfilRepository.save(perfil);
        }

        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.criptografarSenha(request.senha()));
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setPerfil(perfil);

        usuarioRepository.save(usuario);

        return new CriarUsuarioResponse(
                "Usuário cadastrado com sucesso.",
                usuario.getId(), usuario.getNome(), usuario.getEmail(), perfil.getNome(), usuario.getDataCriacao()
        );
    }

    // metodo para autenticar usuario
    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) {

        var senhaCriptografada = cryptoComponent.criptografarSenha(request.senha());

        var usuario = usuarioRepository.findByEmailAndSenha(request.email(), senhaCriptografada);

        if(usuario == null) {
            throw new AcessoNegadoException();
        }
        else {
            return new AutenticarUsuarioResponse(
                    "Usuário autenticado com sucesso.",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getPerfil().getNome(),
                    LocalDateTime.now(),
                    jwtTokenComponent.getExpiration(),
                    jwtTokenComponent.getToken(
                            usuario.getEmail(),
                            usuario.getPerfil().getNome())
            );


        }


    }



}
