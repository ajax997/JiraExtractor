package com.dxc.JiraExtractor.controllers;

import java.util.ArrayList;

import com.dxc.JiraExtractor.JIRAObjects.*;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;
import com.google.gson.Gson;

@RestController
public class RESTAPIController {
	@RequestMapping(value = "api/projects", method = RequestMethod.GET)
	public String getProjects() {
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		ArrayList<JIRAProject> projects = interactor.getProjects();
		return new Gson().toJson(projects);
	}
	
	@RequestMapping(value = "/api/project/{projectId}")
	public String getProjectID(@PathVariable String projectId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		JIRAProjectDetail detail = interactor.getProjectByProjectId(projectId);
		
		return  new Gson().toJson(detail);
		
	}
	
	@RequestMapping(value = "/api/issue/{issueID}")
	public String getIssue(@PathVariable String issueID)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		JIRAIssueDetail detail = interactor.getIssueFromId(issueID);
		return new Gson().toJson(detail);
		
	}
	@RequestMapping(value = "/api/{projectId}/sprints")
	public String getSprint(@PathVariable String projectId)
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

		//TODO
		return new Gson().toJson(sprintResults);
	}

	@RequestMapping(value = "/api/dashboards")
	public String getDashboard()
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		ArrayList<JIRADashboard> dashboards = interactor.getDashboards();
		return new Gson().toJson(dashboards);

	}
	@RequestMapping(value = "/api/dashboard/{dashboardId}")
	public String getDashboardView(@PathVariable String dashboardId)
	{
		JIRAInteractor interactor = new JIRAInteractor(ConfigStuffs.urlString);
		return interactor.getDashboardView(dashboardId);
	}

	@RequestMapping(value = "/api/{projectId}/users")
    public String getUsers(@PathVariable String dashboardId)
    {
        //TODO
        return "";
    }

}
