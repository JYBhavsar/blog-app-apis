package com.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
public class OpenApiConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	
//	private ApiKey apiKeys() {
//		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
//	}
	
//	private List<SecurityContext> securityContexts(){
//		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
//	}
	
//	private List<SecurityReference> sf(){
//		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
//		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{scope}));
//	}
	
	private ApiInfo getInfo() {
		return new ApiInfo("Blog API Jay Bhavsar", "This project is experimental project carried out by R&D team",
				"1.0", "Terms of Services", new Contact("Jay Bhavsar", "info.infinitecastle@gmail.com", "https://infinitecastle.dev"),
				"R&D License", "https://infinitecastle.dev", Collections.emptyList());
	}
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
	
}
