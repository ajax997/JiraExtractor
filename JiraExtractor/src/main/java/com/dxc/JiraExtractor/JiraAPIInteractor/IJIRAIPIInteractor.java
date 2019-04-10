package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.*;

public interface IJIRAIPIInteractor {
	boolean login(String email, String password);
	ArrayList<JIRAProject> getProjects();
	JIRAProject getProjectByName(String jiraName);
	JIRAProjectDetail getProjectByProjectId(String jiraProjId);
	JIRAIssueDetail getIssueFromId(String jiraIssueId);
	ArrayList<JIRADashboard> getDashboards();
	String getDashboardView(String dashboardId);
	String getAllBoard();
	String getAllSprints(int boardID);
	String getSprintFromId(int sprintId);
	ArrayList<JIRASprint> getSprintsFromProjectID(String projectID);
	ArrayList<JIRAProjectUser> getAllUsers();
	
}
