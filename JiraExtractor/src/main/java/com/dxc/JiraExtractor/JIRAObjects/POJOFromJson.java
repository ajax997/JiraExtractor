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
		JIRAProjectUser user = new JIRAProjectUser();
		user.setAccountId(lead.getString("accountId"));
		user.setActive(lead.getBoolean("active"));
		user.setKey(lead.getString("key"));
		user.setSelf(lead.getString("self"));
		user.setName(lead.getString("name"));
		user.setDisplayName(lead.getString("displayName"));
		JSONObject leadAVT = lead.getJSONObject("avatarUrls");
		user.setAvatarUrls(leadAVT.getString("48x48"));
		
		projectDetail.setProjectUser(user);
		
		//ISSUES
		ArrayList<JIRAIssue> listIssues = new ArrayList<JIRAIssue>();
		
		JSONArray issuesArray = json.getJSONArray("issueTypes");
		for(int i = 0 ; i< issuesArray.length(); i++)
		{
			JSONObject issueJ = issuesArray.getJSONObject(i);
			JIRAIssue issue = new JIRAIssue();
			issue.setSelf(issueJ.getString("self"));
			issue.setId(issueJ.getString("id"));
			issue.setDescription(issueJ.getString("description"));
			issue.setIconUrl(issueJ.getString("iconUrl"));
			issue.setName(issueJ.getString("iconUrl"));
			issue.setSubtask(issueJ.getBoolean("subtask"));
			//issue.setAvatarId(issueJ.getInt("avatarId"));
			listIssues.add(issue);
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
			
			version.setName(versionJ.getString("iconUrl"));
			//version.setArchived(versionJ.getBoolean("archived"));
			//version.setReleased(versionJ.getBoolean("released"));
			//version.setProjectId(versionJ.getInt("projectId"));
			
			listVersions.add(version);
		}
		projectDetail.setVersions(listVersions);
		
		return projectDetail;
	}
}
