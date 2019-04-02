package com.dxc.JiraExtractor.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;
import com.google.gson.Gson;

@RestController
public class RESTAPIController {
	@RequestMapping(value = "api/projects", method = RequestMethod.GET)
	public String getProjects() {
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		ArrayList<JIRAProject> projects = interactor.getProjects();
		return new Gson().toJson(projects);
	}
}
