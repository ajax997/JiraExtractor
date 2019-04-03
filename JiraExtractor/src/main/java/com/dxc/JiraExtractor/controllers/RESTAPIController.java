package com.dxc.JiraExtractor.controllers;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.JIRADashboard;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JIRAObjects.JIRAIssueDetail;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;
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
	
	@RequestMapping(value = "/api/project/{projectId}")
	public String getProjectID(@PathVariable String projectId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		JIRAProjectDetail detail = interactor.getProjectByProjectId(projectId);
		
		return  new Gson().toJson(detail);
		
	}
	
	@RequestMapping(value = "/api/issue/{issueID}")
	public String getIssue(@PathVariable String issueID)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		JIRAIssueDetail detail = interactor.getIssueFromId(issueID);
		return new Gson().toJson(detail);
		
	}
	@RequestMapping(value = "/api/{projectId}/sprints")
	public String getSprint(@PathVariable String projectId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		
		//TODO
		return "";
	}

	@RequestMapping(value = "/api/dashboards")
	public String getDashboard()
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		ArrayList<JIRADashboard> dashboards = interactor.getDashboards();
		return new Gson().toJson(dashboards);

	}
	@RequestMapping(value = "/api/dashboard/{dashboardId}")
	public String getDashboardView(@PathVariable String dashboardId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		return interactor.getDashboardView(dashboardId);
	}



}
