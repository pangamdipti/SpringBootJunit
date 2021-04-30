package com.poc6.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc6.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	

	@Query(value="SELECT u FROM User u WHERE CONCAT(u.id,'',u.fname,'',u.pincode)LIKE %?1%")
	public List<User> search(String keyword);

}
