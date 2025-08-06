package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValuel;
	String fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValuel) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValuel));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValuel = fieldValuel;
	}

	public ResourceNotFoundException(String resourceName, String fieldValue) {
		super(String.format("%s not found with '%s'", resourceName, fieldValue));
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}
	
	
}
