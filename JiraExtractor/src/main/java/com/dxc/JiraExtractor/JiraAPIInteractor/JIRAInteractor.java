package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dxc.JiraExtractor.ConfigStuffs;

public class JIRAInteractor implements IJIRAIPIInteractor {

	private String url;
	private String username;
	private String password;

	public JIRAInteractor(String url) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.url = url;
		this.username = username;
	}

	@Override
	public boolean login(String email, String password) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("username", email);
		jsonObject.accumulate("password", password);
		String resultString = SendRequest.sendPOSTRequest(url + "/rest/auth/1/session", jsonObject);
		System.out.println(">>>" + resultString);
		JSONObject jsonResult = new JSONObject(resultString);
		if (jsonResult.has("session")) {
			ConfigStuffs.urlString = url;
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<JIRAProject> getProjects() {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/project", RequestType.GET);
		JSONArray jiraJsonObjectsJsonObject = new JSONArray(resultString);
		ArrayList<JIRAProject> jiraProjects = new ArrayList<JIRAProject>();
		for(int i= 0; i<jiraJsonObjectsJsonObject.length(); i++)
		{
			JSONObject object = jiraJsonObjectsJsonObject.getJSONObject(i);
			jiraProjects.add(POJOFromJson.getJiraProjectFromJson(object));
		}
		return jiraProjects;
	}

	@Override
	public JIRAProject getProjectByName(String jiraName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JIRAProjectDetail getProjectByProjectId(String jiraProjId) {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/project/"+jiraProjId, RequestType.GET);
		JSONObject jiraJsonObjectsJsonObject = new JSONObject(resultString);
		
		return POJOFromJson.getProjectDetailsFromJson(jiraJsonObjectsJsonObject);
		
	}

	@Override
	public JIRAIssueDetail getIssueFromId(String jiraIssueId) {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/issue/"+jiraIssueId, RequestType.GET);
		JSONObject issueJsonObject = new JSONObject(resultString);
		return POJOFromJson.getIssueDetailFromJson(issueJsonObject);
	}

	@Override
	public ArrayList<JIRADashboard> getDashboards() {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/dashboard", RequestType.GET);
		JSONObject jsonObject = new JSONObject(resultString);
		JSONArray dbs = jsonObject.getJSONArray("dashboards");
		ArrayList<JIRADashboard> jiraDashboards = new ArrayList<>();
		for (int i = 0; i< dbs.length(); i++)
		{
			jiraDashboards.add(POJOFromJson.getDashboardFromJson(dbs.getJSONObject(i)));
		}
		return jiraDashboards;
	}

	@Override
	public String getDashboardView(String dashboardId) {
		return SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/dashboard/"+dashboardId, RequestType.GET);
	}


}
