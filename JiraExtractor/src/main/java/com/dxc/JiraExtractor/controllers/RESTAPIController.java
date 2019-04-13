package com.dxc.JiraExtractor.controllers;

import java.util.ArrayList;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.dxc.JiraExtractor.DAO.*;
import com.dxc.JiraExtractor.JIRAObjects.*;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;
import com.google.gson.Gson;

@RestController
public class RESTAPIController {
	@RequestMapping(value = "api/projects", method = RequestMethod.GET)
	public String getProjects() {
//		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
//		ArrayList<JIRAProject> projects = interactor.getProjects();
//		return new Gson().toJson(projects);
		return new Gson().toJson(new JIRAProjectDAO().getAllProject(MYSQLDAOHelper.getConnection(), -1));
	}
	
	@RequestMapping(value = "/api/project/{projectId}")
	public String getProjectID(@PathVariable String projectId)
	{
//		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
//		JIRAProjectDetail detail = interactor.getProjectByProjectId(projectId);
//
//		return  new Gson().toJson(detail);
		return new Gson().toJson(new JIRAProjectDAO().getAllProject(MYSQLDAOHelper.getConnection(), Integer.parseInt(projectId)));
		
	}
	
	@RequestMapping(value = "/api/issue/{issueID}")
	public String getIssue(@PathVariable String issueID)
	{
//		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
//		JIRAIssueDetail detail = interactor.getIssueFromId(issueID);
//		return new Gson().toJson(detail);
		return new Gson().toJson(new JIRAIssueDAO().getAllIssueDetail(MYSQLDAOHelper.getConnection(), Integer.parseInt(issueID)));
		
	}
	@RequestMapping(value = "/api/{projectId}/sprints")
	public String getSprint(@PathVariable String projectId)
	{
//		//TODO
//		return new Gson().toJson(new JIRAInteractor(ConfigStuffs.urlString).getSprintsFromProjectID(projectId));
		return new Gson().toJson(new JIRASprintDAO().getAllSprint(MYSQLDAOHelper.getConnection(), projectId));
	}

	@RequestMapping(value = "/api/{projectId}/issue")
	public String getIssueFromProjectID(@PathVariable String projectId)
	{
		return new Gson().toJson(new JIRAIssueDAO().getAllIssueByProjectID(MYSQLDAOHelper.getConnection(), Integer.parseInt(projectId)));
	}


	@RequestMapping(value = "/api/dashboards")
	public String getDashboard()
	{
//		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
//		ArrayList<JIRADashboard> dashboards = interactor.getDashboards();
//		return new Gson().toJson(dashboards);

		return new Gson().toJson(new JIRADashboardDAO().getAllDashboard(MYSQLDAOHelper.getConnection()));

	}
	@RequestMapping(value = "/api/dashboard/{dashboardId}")
	public String getDashboardView(@PathVariable String dashboardId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		return interactor.getDashboardView(dashboardId);
	}

	@RequestMapping(value = "/api/users")
    public String getUsers()
    {
//		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
//        return new Gson().toJson(interactor.getAllUsers());
		return new Gson().toJson(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.getConnection(), "*"));
    }

    @RequestMapping(value = "/api/user/{userID}")
	public String getUser(@PathVariable String userID)
	{
		return new Gson().toJson(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.getConnection(), userID));
	}

    @RequestMapping(value = "/api/{projectId}/versions")
	public String getVersion(@PathVariable String projectId)
	{
		return new Gson().toJson(new JIRASprintDAO().getAllSprint(MYSQLDAOHelper.getConnection(), projectId));
	}

	@RequestMapping(value = "api/issuetype/{issuetypeID}")
	public String getIssueTypeFromID(@PathVariable String issuetypeID)
	{
		return new Gson().toJson(new JIRAIssueTypeDAO().getAllIssueType(MYSQLDAOHelper.getConnection(), Integer.parseInt(issuetypeID)));
	}

}
