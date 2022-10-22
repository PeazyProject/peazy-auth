package com.laas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class AuthApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@GetMapping(path = "/")
	public String home() {
		return "redirect:./swagger-ui/index.html";
	}

	
}
