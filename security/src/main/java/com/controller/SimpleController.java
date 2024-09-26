package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class SimpleController {

	@GetMapping(value = "")
	public String greeting() {
		return "Welcome to Spring security Api - Public";
	}
}