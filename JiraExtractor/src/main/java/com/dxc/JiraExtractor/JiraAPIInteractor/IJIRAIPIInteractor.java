package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.JIRAIssueDetail;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;

public interface IJIRAIPIInteractor {
	public boolean login(String email, String password);
	public ArrayList<JIRAProject> getProjects();
	public JIRAProject getProjectByName(String jiraName);
	public JIRAProjectDetail getProjectByProjectId(String jiraProjId);
	public JIRAIssueDetail getIssueFromId(String jiraIssueId);
	
}
