package com.springboot.task;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


//Causes Lombok to generate a logger field.
@Slf4j
//Serves two purposes i.e. configuration and bootstrapping.
@SpringBootApplication
public class UserTask {

	public static void main(String[] args) {
		SpringApplication.run(UserTask.class, args);
        log.info("User task application is started successfully .");
    

	}

}
