package com.carscan.service;

import java.util.List;

import com.carscan.entity.User;

public interface UserService {

	List<User> getAllUsers(User user);
	User saveUser(User user);
	User getUserById(int id);
	void deleteUserById(int id);
	User updateUserById(int id, User user);
}
