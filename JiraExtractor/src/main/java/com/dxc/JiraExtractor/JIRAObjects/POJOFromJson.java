package com.dxc.JiraExtractor.JIRAObjects;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class POJOFromJson {
	public static JIRAProjectDetail getProjectDetailsFromJson(JSONObject json) {
		JIRAProjectDetail projectDetail = new JIRAProjectDetail();
		projectDetail.setUrl(json.getString("self"));
		projectDetail.setId(json.getString("id"));
		projectDetail.setKey(json.getString("key"));
		projectDetail.setDescription(json.getString("description"));
		
		projectDetail.setAssigneeType(json.getString("assigneeType"));
		projectDetail.setName(json.getString("name"));
		projectDetail.setProjectType(json.getString("projectTypeKey"));
		projectDetail.setPrivate(json.getBoolean("isPrivate"));
		
		JSONObject jsonAvatars = json.getJSONObject("avatarUrls");
		projectDetail.setAvatarUrl(jsonAvatars.getString("48x48"));
		
		//LEAD
		JSONObject lead = json.getJSONObject("lead");
		projectDetail.setProjectUser(getProjectUserFromJson(lead));
		
		//ISSUES
		ArrayList<JIRAIssue> listIssues = new ArrayList<JIRAIssue>();
		
		JSONArray issuesArray = json.getJSONArray("issueTypes");
		for(int i = 0 ; i< issuesArray.length(); i++)
		{
			JSONObject issueJ = issuesArray.getJSONObject(i);
			
			listIssues.add(getIssueTypeFromJson(issueJ));
		}
		projectDetail.setIssues(listIssues);
		
		//VERSION
		
		ArrayList<JIRAVersion> listVersions = new ArrayList<JIRAVersion>();
		
		JSONArray versionArray = json.getJSONArray("versions");
		for(int i = 0 ; i< versionArray.length(); i++)
		{
			JSONObject versionJ = issuesArray.getJSONObject(i);
			JIRAVersion version = new JIRAVersion();
			version.setSelf(versionJ.getString("self"));
			version.setId(versionJ.getString("id"));
			version.setName(versionJ.getString("name"));
			version.setStartDate(versionJ.getString("startDate"));
			version.setReleaseDate(versionJ.getString("releaseDate"));
			version.setDescription(versionJ.getString("description"));
			
			//version.setArchived(versionJ.getBoolean("archived"));
			//version.setReleased(versionJ.getBoolean("released"));
			//version.setProjectId(versionJ.getInt("projectId"));
			
			listVersions.add(version);
		}
		projectDetail.setVersions(listVersions);
		
		return projectDetail;
	}
	
	public static JIRAIssue getIssueTypeFromJson(JSONObject issueJ) {
		JIRAIssue issue = new JIRAIssue();
		issue.setSelf(issueJ.getString("self"));
		issue.setId(issueJ.getString("id"));
		issue.setDescription(issueJ.getString("description"));
		issue.setIconUrl(issueJ.getString("iconUrl"));
		issue.setName(issueJ.getString("iconUrl"));
		issue.setSubtask(issueJ.getBoolean("subtask"));
		//issue.setAvatarId(issueJ.getInt("avatarId"));
		return issue;
	}	
	
	public static JIRAProjectUser getProjectUserFromJson(JSONObject lead)
	{
		
		JIRAProjectUser user = new JIRAProjectUser();
		user.setAccountId(lead.getString("accountId"));
		user.setActive(lead.getBoolean("active"));
		user.setKey(lead.getString("key"));
		user.setSelf(lead.getString("self"));
		user.setName(lead.getString("name"));
		user.setDisplayName(lead.getString("displayName"));
		JSONObject leadAVT = lead.getJSONObject("avatarUrls");
		user.setAvatarUrls(leadAVT.getString("48x48"));
		return user;
	}
	
	public static JIRAProject getJiraProjectFromJson(JSONObject object)
	{
		JIRAProject jiraProject = new JIRAProject();
		jiraProject.setId(object.getString("id"));
		jiraProject.setUrl(object.getString("self"));
		jiraProject.setName(object.getString("name"));
		jiraProject.setProjectType(object.getString("projectTypeKey"));
		try {
		jiraProject.setPrivate(object.getBoolean("isPrivate"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject avatarUrls = object.getJSONObject("avatarUrls");
		jiraProject.setAvatarUrl(avatarUrls.getString("48x48"));
		
		return jiraProject;
	}
	
	public static JIRAIssueDetail getIssueDetailFromJson(JSONObject object)
	{
		JSONObject contentJ = object.getJSONObject("fields");
		
		JIRAIssueDetail jiraDetail = new JIRAIssueDetail();
		jiraDetail.setId(object.getString("id"));
		jiraDetail.setKey(object.getString("key"));
		//jiraDetail.setSummary(object.getString("summary"));
		jiraDetail.setProject(POJOFromJson.getJiraProjectFromJson(contentJ.getJSONObject("project")));
		jiraDetail.setIssueType(POJOFromJson.getIssueTypeFromJson(contentJ.getJSONObject("issuetype")));
		
		jiraDetail.setCreator(POJOFromJson.getProjectUserFromJson(contentJ.getJSONObject("creator")));
		jiraDetail.setReporter(POJOFromJson.getProjectUserFromJson(contentJ.getJSONObject("reporter")));
		
		try {
			JSONObject j = contentJ.getJSONObject("assignee");
			jiraDetail.setAssignee(POJOFromJson.getProjectUserFromJson(j));
		}
		catch(Exception e) {e.printStackTrace();}
		
		return jiraDetail;
	}

	public static JIRADashboard getDashboardFromJson(JSONObject json){
		JIRADashboard jiraDashboard = new JIRADashboard();
		jiraDashboard.setId(json.getString("id"));
		jiraDashboard.setName(json.getString("name"));
		jiraDashboard.setUrl(json.getString("self"));
		jiraDashboard.setView(json.getString("view"));
		return  jiraDashboard;

	}
	
}
