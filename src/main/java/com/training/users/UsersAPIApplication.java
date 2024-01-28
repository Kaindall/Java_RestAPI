package com.training.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class UsersAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersAPIApplication.class, args);
		System.out.println("Users: OK!");
	}
}
