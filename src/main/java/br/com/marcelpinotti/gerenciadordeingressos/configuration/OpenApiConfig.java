package br.com.marcelpinotti.gerenciadordeingressos.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI projetoAPI() {
        return new OpenAPI()
                .info(new Info().title("Gerenciador de Ingressos")
                        .description("Rest API de gerenciamento de ingressos")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
