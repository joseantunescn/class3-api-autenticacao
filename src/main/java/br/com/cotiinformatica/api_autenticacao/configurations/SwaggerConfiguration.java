package br.com.cotiinformatica.api_autenticacao.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticação de Usuários")
                        .version("v1")
                        .description("""
                            API REST desenvolvida com Java, Spring Boot e Spring Data
                            para cadastro e autenticação de usuários,
                            com perfis e permissões de acesso.

                            Projeto: api-autenticacao

                            Funcionalidades:
                            - Cadastro de usuários.
                            - Autenticação de usuários mediante e-mail e senha.
                            - Associação de usuários a perfis de acesso.
                            - Definição de permissões por perfil.
                            - Controle de acesso conforme perfis e permissões.
                            - Validação de dados e regras de negócio.
                            - Integração com banco de dados PostgreSQL.

                            Projeto desenvolvido para fins educacionais
                            durante o curso Java WebDeveloper Fullstack
                            da COTI Informática.

                            Esta documentação apresenta os endpoints,
                            parâmetros, modelos de dados e respostas HTTP
                            disponibilizados pela aplicação.
                            """)
                        .contact(new Contact()
                                .name("COTI Informática")
                                .url("https://www.cotiinformatica.com.br")
                        )
                );
    }
}