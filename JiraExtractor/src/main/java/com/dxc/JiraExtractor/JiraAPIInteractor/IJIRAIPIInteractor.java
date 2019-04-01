package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;

public interface IJIRAIPIInteractor {
	public boolean login(String email, String password);
	public ArrayList<JIRAProject> getProjects();
	public JIRAProject getProjectByName(String jiraName);
	public JIRAProject getProjecyByProjectId(String jiraProjId);
}
