package com.springboot.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.task.model.User;
import com.springboot.task.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		//log.info("Gettin all users from database");
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public void saveUser(User user) {
		userRepository.save(user);
		
	}

	public User getUserById(int id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException("User not found for id: " + id);
		}
		return user;
	}

	public void deleteUserById(int id) {
		this.userRepository.deleteById(id);
		
	}

	public List<User> listAll(String keyword) {
		List<User> users = new ArrayList<User>();
		if(keyword != null) {
			userRepository.search(keyword).forEach(users::add);
			return users;
		}
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public void updateUser(User user, int id) {
		userRepository.save(user);
	}

	
}
