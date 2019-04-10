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

	POJOFromJson pojoFromJson = new POJOFromJson();
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
			jiraProjects.add(pojoFromJson.getJiraProjectFromJson(object));
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

		return pojoFromJson.getProjectDetailsFromJson(jiraJsonObjectsJsonObject);

	}

	@Override
	public JIRAIssueDetail getIssueFromId(String jiraIssueId) {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/issue/"+jiraIssueId, RequestType.GET);
		JSONObject issueJsonObject = new JSONObject(resultString);
		return pojoFromJson.getIssueDetailFromJson(issueJsonObject);
	}

	@Override
	public ArrayList<JIRADashboard> getDashboards() {
		String resultString = SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/dashboard", RequestType.GET);
		JSONObject jsonObject = new JSONObject(resultString);
		JSONArray dbs = jsonObject.getJSONArray("dashboards");
		ArrayList<JIRADashboard> jiraDashboards = new ArrayList<>();
		for (int i = 0; i< dbs.length(); i++)
		{
			jiraDashboards.add(pojoFromJson.getDashboardFromJson(dbs.getJSONObject(i)));
		}
		return jiraDashboards;
	}

	@Override
	public String getDashboardView(String dashboardId) {
		return SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/3/dashboard/"+dashboardId, RequestType.GET);
	}

	@Override
	public String getAllBoard() {
		return SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/greenhopper/1.0/rapidview", RequestType.GET);
	}

	@Override
	public String getAllSprints(int boardID) {
		return SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/agile/1.0/board/"+boardID+"/sprint", RequestType.GET);
	}

	@Override
	public String getSprintFromId(int sprintId) {
		return SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/2/search?jql=Sprint="+sprintId, RequestType.GET);
	}



	@Override
	public ArrayList<JIRASprint> getSprintsFromProjectID(String projectId)
	{
		POJOFromJson pojoFromJson = new POJOFromJson();
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		JSONObject boards = new JSONObject(interactor.getAllBoard());
		JSONArray views = boards.getJSONArray("views");

		ArrayList<JIRASprint> sprintArray = new ArrayList<>();
		for(int i = 0 ; i< views.length(); i++)
		{
			JSONObject board = views.getJSONObject(i);
			if (board.getBoolean("sprintSupportEnabled")){
				JSONObject sprints = new JSONObject(interactor.getAllSprints(board.getInt("id")));
				JSONArray sprintList = sprints.getJSONArray("values");
				for (int j = 0; j< sprintList.length(); j++)
				{
					JSONObject object = sprintList.getJSONObject(j);
					sprintArray.add(pojoFromJson.getSprintFromJson(object));
				}
			}
		}
		ArrayList<JIRASprint> sprintResults = new ArrayList<>();
		for (JIRASprint sprint: sprintArray){
			JSONObject sprintDetails = new JSONObject(interactor.getSprintFromId(sprint.getId()));
			JSONArray sprintDetailsIssue = sprintDetails.getJSONArray("issues");
			if(sprintDetailsIssue.length()>=1)
			{
				JSONObject projectOfIssue = sprintDetailsIssue.getJSONObject(0).getJSONObject("fields").getJSONObject("project");

				ArrayList<JIRAIssueDetail> issueDetailsOfObject = new ArrayList<>();
				for (int i = 0; i< sprintDetailsIssue.length(); i++)
				{
					JIRAIssueDetail issue = pojoFromJson.getIssueDetailFromJson(sprintDetailsIssue.getJSONObject(i));
					issueDetailsOfObject.add(issue);
				}
				sprint.setIssues(issueDetailsOfObject);

				if(projectOfIssue.getString("id").equals(projectId))
				{
					sprintResults.add(sprint);
				}
			}
		}
		return sprintResults;
	}

	@Override
	public ArrayList<JIRAProjectUser> getAllUsers()
	{
		ArrayList<JIRAProjectUser> projectUsers = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(SendRequest.sendRequest(ConfigStuffs.urlString + "/rest/api/2/user/search?username=%25&startAt=0&maxResults=1000", RequestType.GET));

		for(int i =0 ; i< jsonArray.length(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.getString("accountType").equals("atlassian"))
			{
				JIRAProjectUser projectUser = pojoFromJson.getAccountFromJSON(jsonObject);
				projectUsers.add(projectUser);
			}
		}

		return projectUsers;
	}

}
