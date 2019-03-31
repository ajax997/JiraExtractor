package com.dxc.JiraExtractor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping("/")
	public String indexPage()
	{
		return "<h1>Hello World!</h1>";
	}
}
