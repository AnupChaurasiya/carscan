package com.carscan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carscan.entity.User;
import com.carscan.repository.UserRepository;
import com.carscan.service.Impl.UserServiceImpl;

@RestController
@RequestMapping("/carscan")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/getusers")
	public List<User> getAllUsers(User user){
		return userServiceImpl.getAllUsers(user);
	}
	@PostMapping("/saveuser")
	public void saveUser(@RequestBody User user) {
		userServiceImpl.saveUser(user);
	}
	@GetMapping("/oneuser/{id}")
	public User getUserById(@PathVariable (value="id") int id) {
		return userServiceImpl.getUserById(id);
	}
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUserById(@PathVariable (value="id") int id) {
		userServiceImpl.deleteUserById(id);
		System.out.println("user deleted successfully!!");
	}
	@PutMapping("/update/{id}")
	public User updateUserById(@PathVariable (value="id") int id, @RequestBody User user) {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			User newUser = opt.get();
			newUser.setFname(user.getFname());
			newUser.setLname(user.getLname());
			newUser.setDob(user.getDob());
			newUser.setMobileNo(user.getMobileNo());	
			return userRepository.save(newUser);
		}
		
		return userRepository.save(user);
		
	}
}
