package com.dxc.JiraExtractor.JIRAObjects;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class POJOFromJson {
	public JIRAProjectDetail getProjectDetailsFromJson(JSONObject json) {
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
		ArrayList<JIRAIssueType> listIssues = new ArrayList<JIRAIssueType>();

		JSONArray issuesArray = json.getJSONArray("issueTypes");
		for (int i = 0; i < issuesArray.length(); i++) {
			JSONObject issueJ = issuesArray.getJSONObject(i);

			listIssues.add(getIssueTypeFromJson(issueJ));
		}
		projectDetail.setIssues(listIssues);

		//VERSION

		ArrayList<JIRAVersion> listVersions = new ArrayList<JIRAVersion>();

		JSONArray versionArray = json.getJSONArray("versions");
		for (int i = 0; i < versionArray.length(); i++) {
			JSONObject versionJ = versionArray.getJSONObject(i);
			JIRAVersion version = getVersionFromJson(versionJ);
			listVersions.add(version);
		}
		projectDetail.setVersions(listVersions);

		return projectDetail;
	}

	public JIRAVersion getVersionFromJson(JSONObject versionJ) {
		JIRAVersion version = new JIRAVersion();
		version.setSelf(versionJ.getString("self"));
		version.setId(versionJ.getString("id"));
		version.setName(versionJ.getString("name"));
		version.setProjectId(versionJ.getInt("projectId"));
		try {
			version.setStartDate(versionJ.getString("startDate"));
			version.setReleaseDate(versionJ.getString("releaseDate"));
			version.setDescription(versionJ.getString("description"));
		} catch (Exception e) {
			System.out.println("ISSUE " + versionJ.getString("id") + " have no description");
		}
		version.setArchived(versionJ.getBoolean("archived"));
		version.setReleased(versionJ.getBoolean("released"));

		return version;

	}

	public JIRAIssueType getIssueTypeFromJson(JSONObject issueJ) {
		JIRAIssueType issue = new JIRAIssueType();
		issue.setSelf(issueJ.getString("self"));
		issue.setId(issueJ.getString("id"));
		issue.setDescription(issueJ.getString("description"));
		issue.setIconUrl(issueJ.getString("iconUrl"));
		issue.setName(issueJ.getString("name"));
		issue.setSubtask(issueJ.getBoolean("subtask"));
		//issue.setAvatarId(issueJ.getInt("avatarId"));
		return issue;
	}

	public JIRAProjectUser getProjectUserFromJson(JSONObject lead) {

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

	public JIRAProject getJiraProjectFromJson(JSONObject object) {
		JIRAProject jiraProject = new JIRAProject();
		jiraProject.setId(object.getString("id"));
		jiraProject.setKey(object.getString("key"));
		jiraProject.setUrl(object.getString("self"));
		jiraProject.setName(object.getString("name"));
		jiraProject.setProjectType(object.getString("projectTypeKey"));
		try {
			jiraProject.setPrivate(object.getBoolean("isPrivate"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject avatarUrls = object.getJSONObject("avatarUrls");
		jiraProject.setAvatarUrl(avatarUrls.getString("48x48"));

		return jiraProject;
	}

	public JIRAIssueDetail getIssueDetailFromJson(JSONObject object) {
		JIRAIssueDetail jiraDetail = new JIRAIssueDetail();

		jiraDetail.setSelf(object.getString("self"));
		JSONObject contentJ = object.getJSONObject("fields");

		if (contentJ.has("parent")) {
			String key = "parent";
			JSONObject jParent = contentJ.getJSONObject(key);
			jiraDetail.setParentID(jParent.getString("id"));
		}

		if (contentJ.has("fixVersions")) {
			String key = "fixVersions";
			JSONArray jFixVersion = contentJ.getJSONArray(key);
			if (jFixVersion.length() >= 1)
				jiraDetail.setFixVersions(jFixVersion.getJSONObject(0).getString("id"));
		}

		if (contentJ.has("customfield_10021")) {

			Object e = (contentJ.get("customfield_10021"));
			if (e.getClass().getName().equals("org.json.JSONArray")) {
				String key = "customfield_10021";
				JSONArray j = contentJ.getJSONArray(key);
				String f = (j.get(0).toString());
				Pattern p = Pattern.compile("(id=\\d+)");
				Matcher m = p.matcher(f);
				while (m.find()) {
					String x = (m.group(0));
					jiraDetail.setSprintID(x.substring(3));
					break;

				}

			}
		}


		jiraDetail.setId(object.getString("id"));
		jiraDetail.setKey(object.getString("key"));
		try {
			jiraDetail.setSummary(object.getString("summary"));
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		jiraDetail.setProject(getJiraProjectFromJson(contentJ.getJSONObject("project")));

		JSONObject jsonObject = contentJ.getJSONObject("project");

		jiraDetail.setIssueType(getIssueTypeFromJson(contentJ.getJSONObject("issuetype")));

		jiraDetail.setCreator(getProjectUserFromJson(contentJ.getJSONObject("creator")));
		jiraDetail.setReporter(getProjectUserFromJson(contentJ.getJSONObject("reporter")));


		try {
			Object aObj = contentJ.get("assignee");
			if (aObj instanceof JSONObject) {
				JSONObject j = contentJ.getJSONObject("assignee");
				jiraDetail.setAssignee(getProjectUserFromJson(j));
			} else {
				jiraDetail.setAssignee(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jiraDetail;
	}

	public JIRADashboard getDashboardFromJson(JSONObject json) {
		JIRADashboard jiraDashboard = new JIRADashboard();
		jiraDashboard.setId(json.getString("id"));
		jiraDashboard.setName(json.getString("name"));
		jiraDashboard.setUrl(json.getString("self"));
		jiraDashboard.setView(json.getString("view"));
		return jiraDashboard;

	}

	public JIRASprint getSprintFromJson(JSONObject jsonObject) {
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
		JIRAProjectUser jiraProjectUser = new JIRAProjectUser();
		jiraProjectUser.setAccountId(jsonObject.getString("accountId"));
		jiraProjectUser.setKey(jsonObject.getString("key"));
		jiraProjectUser.setName(jsonObject.getString("name"));
		jiraProjectUser.setDisplayName(jsonObject.getString("displayName"));
		jiraProjectUser.setActive(jsonObject.getBoolean("active"));

		JSONObject avtJ = jsonObject.getJSONObject("avatarUrls");

		jiraProjectUser.setAvatarUrls(avtJ.getString("48x48"));
		return jiraProjectUser;
	}
}
