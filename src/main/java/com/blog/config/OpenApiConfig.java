package com.blog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = @Info(title = "Blog API Infinite Castle", 
		version = "1.0", 
		description = "This project is experimental project carried out by R&D team", 
		contact = @io.swagger.v3.oas.annotations.info.Contact(
				name = "Jay Bhavsar",
				email = "info.infinitecastle@gmail.com",
				url = "https://infinitecastle.dev"
				), 
		termsOfService = "Terms of Services", 
		license = @io.swagger.v3.oas.annotations.info.License(name = "R&D License", 
			url = "https://infinitecastle.dev"
		), 
		extensions = @io.swagger.v3.oas.annotations.extensions.Extension(
					name = "R&D License", 
					properties = {
							@ExtensionProperty(name = "", value = "") }

		))
)
public class OpenApiConfig {

}
