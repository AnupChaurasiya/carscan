package com.carscan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<User>> getAllUsers(User user){
		return new ResponseEntity<> (userServiceImpl.getAllUsers(user), HttpStatus.OK);
	}
	@PostMapping("/saveuser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userServiceImpl.saveUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	@GetMapping("/oneuser/{id}")
	public User getUserById(@PathVariable (value="id") int id) {
		return userServiceImpl.getUserById(id);
	}
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable (value="id") int id) {
		try {
			userServiceImpl.deleteUserById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<User>  updateUserById(@PathVariable (value="id") int id, @RequestBody User user) {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			User newUser = opt.get();
			newUser.setFname(user.getFname());
			newUser.setLname(user.getLname());
			newUser.setDob(user.getDob());
			newUser.setMobileNo(user.getMobileNo());	
			return new ResponseEntity<User>(userRepository.save(newUser), HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		
	}
}
