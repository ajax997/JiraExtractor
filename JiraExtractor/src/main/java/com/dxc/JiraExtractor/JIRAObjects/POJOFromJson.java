package com.dxc.JiraExtractor.JIRAObjects;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class POJOFromJson {
	public  JIRAProjectDetail getProjectDetailsFromJson(JSONObject json) {
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
			try {
				version.setStartDate(versionJ.getString("startDate"));
				version.setReleaseDate(versionJ.getString("releaseDate"));
				version.setDescription(versionJ.getString("description"));
				version.setArchived(versionJ.getBoolean("archived"));
				version.setReleased(versionJ.getBoolean("released"));
				version.setProjectId(versionJ.getInt("projectId"));
			}
			catch (Exception e)
			{
				System.out.println("Error at HERE");
			}


			
			listVersions.add(version);
		}
		projectDetail.setVersions(listVersions);
		
		return projectDetail;
	}
	
	public  JIRAIssue getIssueTypeFromJson(JSONObject issueJ) {
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
	
	public  JIRAProjectUser getProjectUserFromJson(JSONObject lead)
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
	
	public  JIRAProject getJiraProjectFromJson(JSONObject object)
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
	
	public  JIRAIssueDetail getIssueDetailFromJson(JSONObject object)
	{
		JSONObject contentJ = object.getJSONObject("fields");
		
		JIRAIssueDetail jiraDetail = new JIRAIssueDetail();
		jiraDetail.setId(object.getString("id"));
		jiraDetail.setKey(object.getString("key"));
		//jiraDetail.setSummary(object.getString("summary"));
		jiraDetail.setProject(getJiraProjectFromJson(contentJ.getJSONObject("project")));
		jiraDetail.setIssueType(getIssueTypeFromJson(contentJ.getJSONObject("issuetype")));
		
		jiraDetail.setCreator(getProjectUserFromJson(contentJ.getJSONObject("creator")));
		jiraDetail.setReporter(getProjectUserFromJson(contentJ.getJSONObject("reporter")));


		try {
			JSONObject j = contentJ.getJSONObject("assignee");
			jiraDetail.setAssignee(getProjectUserFromJson(j));
		}
		catch(Exception e) {
			jiraDetail.setAssignee(new JIRAProjectUser());
		}
		
		return jiraDetail;
	}

	public  JIRADashboard getDashboardFromJson(JSONObject json){
		JIRADashboard jiraDashboard = new JIRADashboard();
		jiraDashboard.setId(json.getString("id"));
		jiraDashboard.setName(json.getString("name"));
		jiraDashboard.setUrl(json.getString("self"));
		jiraDashboard.setView(json.getString("view"));
		return  jiraDashboard;

	}

	public  JIRASprint getSprintFromJson(JSONObject jsonObject)
	{
		JIRASprint sprint = new JIRASprint();
		sprint.setId(jsonObject.getInt("id"));
		sprint.setState(jsonObject.getString("state"));
		sprint.setName(jsonObject.getString("name"));
		//sprint.setStartDate(jsonObject.getString("startDate"));
		//sprint.setEndDate(jsonObject.getString("endDate"));
		//sprint.setGoal(jsonObject.getString("goal"));
		return sprint;

	}

    public JIRAProjectUser getAccountFromJSON(JSONObject jsonObject) {
		JIRAProjectUser jiraProjectUser= new JIRAProjectUser();
		jiraProjectUser.setAccountId(jsonObject.getString("accountId"));
		jiraProjectUser.setKey(jsonObject.getString("key"));
		jiraProjectUser.setName(jsonObject.getString("name"));
		jiraProjectUser.setDisplayName(jsonObject.getString("displayName"));
		jiraProjectUser.setActive(jsonObject.getBoolean("active"));


		JSONObject avtJ = jsonObject.getJSONObject("avatarUrls");

		jiraProjectUser.setAvatarUrls(avtJ.getString("48x48"));
		return  jiraProjectUser;
    }
}
