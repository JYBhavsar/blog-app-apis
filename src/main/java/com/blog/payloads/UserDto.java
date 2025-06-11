package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	/*
	 * DTOs Objects used to transfer data between layers of your application 
	 * (e.g., from the service layer to the controller layer).
	 * */
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Email( message = "Email address is not valid!!")
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String about;
	
	
}
