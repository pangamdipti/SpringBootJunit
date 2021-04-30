package com.springboot.task.service;

import java.util.List;

import com.springboot.task.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public void saveUser(User user);
	public User getUserById(int id);
	public void deleteUserById(int id);
	public List<User> listAll(String keyword);

}
