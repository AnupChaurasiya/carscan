package com.carscan.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carscan.entity.User;
import com.carscan.repository.UserRepository;
import com.carscan.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers(User user) {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User getUserById(int id) {
		Optional<User> opt = userRepository.findById(id);
		User user = new User();
		if (opt.isPresent()) {
			user = opt.get();
		} else {
			System.out.println("data not found for id: " + id);
		}
		return user;
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);	
	}

	@Override
	public User updateUserById(int id, User user) {
		return userRepository.findById(id).get();	
	}
}
