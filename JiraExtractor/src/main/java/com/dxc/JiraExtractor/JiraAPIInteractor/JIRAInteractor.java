package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;

public class JIRAInteractor implements IJIRAIPIInteractor{

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
		System.out.println(">>>"+resultString);
		JSONObject jsonResult = new JSONObject(resultString);
		if (jsonResult.has("session"))
			return true;
		else {
			return false;
		}
	}

	@Override
	public ArrayList<JIRAProject> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JIRAProject getProjectByName(String jiraName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JIRAProject getProjecyByProjectId(String jiraProjId) {
		// TODO Auto-generated method stub
		return null;
	}

}
