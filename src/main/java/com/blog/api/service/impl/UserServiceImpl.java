package com.blog.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.User;
import com.blog.api.payload.UserDto;
import com.blog.api.repository.UserRepo;
import com.blog.api.service.UserService;
import com.blog.api.exception.*;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);

		User returnUser = userRepository.save(user);

		return this.userToDto(returnUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		User updatedUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepository.findAll();
		List<UserDto> dtoUsers = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return dtoUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());

		return userDto;
	}

}
