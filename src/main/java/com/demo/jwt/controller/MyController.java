package com.demo.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/status")
	public String statusCheck() {
		return "welcome";
	}
}
