package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.UserDto;
import com.blog.api.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userserviceImpl;

	@RequestMapping("/")
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)

	{
		UserDto createdUser = this.userserviceImpl.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

}
