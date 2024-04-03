package com.safedose.apimedicamentos.config.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("medicamentos_api")
						.version("0.0.1")
						.description("Api para controle de medicamentos e farmacia hospitalar.")
						.termsOfService("https://github.com/debsdamaso/ProjetoSD")
						.contact(new Contact()
								.name("Contato por email")
								.email("deboracert@gmail.com"))
						).components(new Components()
								.addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")
								)
						).security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth")));
			}

}
