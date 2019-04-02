package com.dxc.JiraExtractor.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;

@Controller
public class WelcomeController {

	// inject via application.properties

	@RequestMapping("/")
	public String welcome() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("link") String url,@RequestParam("email") String user, @RequestParam("pass") String password) {
		JIRAInteractor interactor = new JIRAInteractor(url);
		boolean loginR = interactor.login(user, password);
		if (loginR)
			return "project";
		else
		{
			return "xxx";
		}
	}
}
