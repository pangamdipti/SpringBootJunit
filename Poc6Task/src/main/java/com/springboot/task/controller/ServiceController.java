package com.springboot.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.task.model.User;
import com.springboot.task.service.UserService;

@RestController
public class ServiceController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	//get all users
	@GetMapping("/users")
	public List<User> getUsers(){
		logger.info("Displaying all users");

		return userService.getAllUsers();
	}
	
	//save user
	@PostMapping("/users")
	public void addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("Invalid inputs...!!");
		}
		logger.info("Adding new user...");
		userService.saveUser(user);
	}
	
	//search user by id or name or pincode
	@GetMapping("/users/{keyword}")
	public List<User> searchUser(@PathVariable String keyword) {
		logger.info("Searching user...");

		return userService.listAll(keyword);

	}
	
	//edit base on user id
	@PutMapping("/users/{id}")
	public void updateUser(@RequestBody User user, @PathVariable int id) {
		logger.info("Updating new user...");

		userService.saveUser(user);
	}
	
	//delete base on user id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		logger.warn("Deleting user...");

		userService.deleteUserById(id);
	}
	

}
