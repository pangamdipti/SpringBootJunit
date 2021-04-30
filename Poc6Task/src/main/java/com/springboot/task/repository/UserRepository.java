package com.springboot.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.task.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="SELECT u FROM User u WHERE CONCAT(u.id,'',u.fname,'',u.pincode)LIKE %?1%")
	public List<User> search(String keyword);


}
