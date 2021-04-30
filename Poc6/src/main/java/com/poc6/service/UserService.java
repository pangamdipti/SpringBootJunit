package com.poc6.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.poc6.model.User;


public interface UserService {
	
	List<User> getAllUsers(String keyword);
	void saveUser(User user);
	User getUserById(int id);
	void deleteUserById(int id);
	Page<User> getPaginatedUsers(int pageNumber, int pageSize);
	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	//public List<User> listAll(String keyword);

}
