package com.generation.aplicativofitness.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto Aplicativo Fitness")
					.description("Projeto Aplicativo Fitness - Grupo 07")
					.version("v0.0.1")
					.license(new License()
							.name("Grupo 07l")
							.url("https://brazil.Grupo07org/"))
					.contact(new Contact()
							.name("Grupo 07")
							.url("https://github.com/Projeto-Integrador-Grupo7")
							.email("generation.g777@gmail.com") ))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/Projeto-Integrador-Grupo7"));
	
	}
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem. readOperations()
				.forEach(operation -> {
				
					ApiResponses apiResponses = operation.getResponses();

					apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
					apiResponses. addApiResponse("201", createApiResponse("Objeto Persistido!"));
					apiResponses. addApiResponse("204", createApiResponse("Objeto Excluido!"));
					apiResponses.addApiResponse("400", createApiResponse("Erro na Requisicao!"));
					apiResponses. addApiResponse("401", createApiResponse("Acesso Nao Autorizado!"));
					apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
					apiResponses. addApiResponse("404", createApiResponse("Objeto Nao Encontrado!"));
					apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicacao!"));
				}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
}
	
