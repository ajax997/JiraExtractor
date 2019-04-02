package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.util.ArrayList;

import javax.xml.ws.spi.http.HttpContext;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;

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
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/2/project", RequestType.GET);
		JSONArray jiraJsonObjectsJsonObject = new JSONArray(resultString);
		ArrayList<JIRAProject> jiraProjects = new ArrayList<JIRAProject>();
		for(int i= 0; i<jiraJsonObjectsJsonObject.length(); i++)
		{
			JSONObject object = jiraJsonObjectsJsonObject.getJSONObject(i);
			
			JIRAProject jiraProject = new JIRAProject();
			jiraProject.setId(object.getString("id"));
			jiraProject.setUrl(object.getString("self"));
			jiraProject.setName(object.getString("name"));
			jiraProject.setProjectType(object.getString("projectTypeKey"));
			jiraProject.setPrivate(object.getBoolean("isPrivate"));
			
			JSONObject avatarUrls = object.getJSONObject("avatarUrls");
			jiraProject.setAvatarUrl(avatarUrls.getString("48x48"));
			
			jiraProjects.add(jiraProject);
		}
		return jiraProjects;
		
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
