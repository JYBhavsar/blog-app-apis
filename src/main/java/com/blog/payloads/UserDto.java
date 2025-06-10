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
	@Size(min=2, message =  "Username should be minimum of 2 characters")
	private String name;
	
	@Email( message = "Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10 , message = "Password must be minimum 3 chars to maximum 20 chars")
	private String password;
	
	@NotNull
	@NotEmpty
	private String about;
	
	
}
