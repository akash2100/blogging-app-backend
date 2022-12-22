package com.blog.api.payload;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDto {
	
	private int id;
	private String name;
	private String password;
	private String email;
	private String about;

}
