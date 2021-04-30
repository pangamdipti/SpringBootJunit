package com.poc6.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poc6.model.User;
import com.poc6.service.UserService;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private UserService userService;

	// display list of users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		logger.info("Redirecting the index page to the controller method for fetching the users in a paginated fashion.");
		return findPaginated(1,"fname","asc",model);

		/*
		 * model.addAttribute("listUsers", userService.getAllUsers()); return "index";
		 */
	}

	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {

		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUsers", listUsers);
		return "index";
	}
	


	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		logger.info("Adding new user...");

		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("Invalid inputs...!!");
			return "new_user";
		}
		logger.info("Saving new user...");
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
		logger.info("Updating new user...");
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "update_user";

	}
	
	@GetMapping("/updateUser")
	public String updateUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("Invalid inputs...!!");
			return "update_user";
		}
		logger.info("Updating user...");
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") int id) {
		logger.warn("Deleting user...");
		this.userService.deleteUserById(id);
		return "redirect:/";
	}
	
	@GetMapping("/User/search")
	public String searchUser(Model model, @Param("keyword") String keyword) {
		logger.info("Searching user...");
		List<User> listUsers = userService.getAllUsers(keyword);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("keyword", keyword);
		return "index";
	}
}
