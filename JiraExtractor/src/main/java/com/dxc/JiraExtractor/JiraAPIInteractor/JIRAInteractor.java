package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JIRAObjects.JIRAIssueDetail;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;
import com.dxc.JiraExtractor.JIRAObjects.POJOFromJson;

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

}
