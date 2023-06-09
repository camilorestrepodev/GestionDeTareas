package com.nttdata.microserviciousuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD DE USUARIOS")
                        .description("API para gestionar usuarios")
                        .version("1.0.0"));
    }

}
