package com.dxc.JiraExtractor.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.DAO.*;
import com.dxc.JiraExtractor.JIRAObjects.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RedirectController {

	// inject via application.properties

	@RequestMapping("/")
	public String welcome() {
		if(ConfigStuffs.login)
			return "Projects";
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("link") String url,@RequestParam("email") String user, @RequestParam("token") String token) {
		JIRAInteractor interactor = new JIRAInteractor(url);
		boolean loginR = interactor.login(user, token);
		if (loginR) {
			new ManipulationDatabase().dropTables();
			new ManipulationDatabase().addTables();
			ConfigStuffs.login = true;
			ConfigStuffs.urlString = url;
			ConfigStuffs.email = user;
			String originalInput = user + ":" + token;
			ConfigStuffs.tokenBase64 = Base64.getEncoder().encodeToString(originalInput.getBytes());
			return "Projects";
		}
		else
		{
			return "login";
		}
	}

	@RequestMapping(value = "/import")
	public String importProject() {
//		if(!ConfigStuffs.login)
//			return "login";
		new ManipulationDatabase().dropTables();
		new ManipulationDatabase().addTables();
		
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);

		//IMPORT USER
		JIRAAccountDAO jiraAccountDAO = new JIRAAccountDAO();
		for (JIRAProjectUser projectUser: interactor.getAllUsers())
		{
			jiraAccountDAO.addAccount(MYSQLDAOHelper.getConnection(), projectUser);
			System.out.println("IMPORT USER COMPLETED");
		}

		//IMPORT PROJECT

		ArrayList<JIRAProject> projects = interactor.getProjects();
		JIRAProjectDAO projectDAO = new JIRAProjectDAO();
		JIRAProjectDetailDAO projectDetailDAO = new JIRAProjectDetailDAO();

		for (JIRAProject project : projects) {
			projectDAO.addProject(MYSQLDAOHelper.getConnection(), project);

			//IMPORT PROJECT DETAILS
			JIRAProjectDetail detail = interactor.getProjectByProjectId(project.getId());
			projectDetailDAO.addProjectDetail(MYSQLDAOHelper.getConnection(), detail);

			//IMPORT SPRINTS
			JIRASprintDAO jiraSprintDAO = new JIRASprintDAO();
			for (JIRASprint sprint : interactor.getSprintsFromProjectID(project.getId())) {

				jiraSprintDAO.addSprint(MYSQLDAOHelper.getConnection(), sprint, Integer.parseInt(project.getId()));
			}

			//IMPORT VERSION
			JIRAVersionDAO jiraVersionDAO = new JIRAVersionDAO();
			for (JIRAVersion jiraVersion : detail.getVersions()) {
				jiraVersionDAO.addVersion(MYSQLDAOHelper.getConnection(), jiraVersion);
			}

			//IMPORT ISSUES
			JIRAIssueTypeDAO issueTypeDAO = new JIRAIssueTypeDAO();
			for (JIRAIssueType issue : detail.getIssues()) {
				issueTypeDAO.addIssue(MYSQLDAOHelper.getConnection(), issue);
			}

			//IMPORT ISSUE DETAILS
			JIRAIssueDAO issueDAO = new JIRAIssueDAO();
			for (JIRAIssueDetail issue : interactor.getIssueFromProjectKey(project.getKey())) {

				issueDAO.addIssue(MYSQLDAOHelper.getConnection(), interactor.getIssueFromId(issue.getId()));
			}
		}

		//IMPORT DASHBOARD
		ArrayList<JIRADashboard> dashboards = interactor.getDashboards();
		JIRADashboardDAO dashboardDAO = new JIRADashboardDAO();
		for (JIRADashboard dashboard : dashboards) {
			dashboardDAO.addDashboard(MYSQLDAOHelper.getConnection(), dashboard);
		}

		return "Projects";
	}


	@RequestMapping(value = "/logout")
	public String logout()
	{
        new ManipulationDatabase().dropTables();
        ConfigStuffs.login = false;
        return "login";
	}
}
