package com.blog.api.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	
	public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue)
	
	{
		super(String.format("%s Not Found with %s : %l", resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
	
}
