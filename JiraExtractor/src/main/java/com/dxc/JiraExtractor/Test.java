package com.dxc.JiraExtractor;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dxc.JiraExtractor.DAO.ManipulationDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws SQLException {
//		String j = " {\"expand\":\"description,lead,issueTypes,url,projectKeys\",\"self\":\"https://jraproj.atlassian.net/rest/api/3/project/10001\",\"id\":\"10001\",\"key\":\"FP\",\"description\":\"\",\"lead\":{\"self\":\"https://jraproj.atlassian.net/rest/api/3/user?accountId=5c99d5e4c377b1118a1b6261\",\"key\":\"admin\",\"accountId\":\"5c99d5e4c377b1118a1b6261\",\"name\":\"admin\",\"avatarUrls\":{\"16x16\":\"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=16&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D16%26noRedirect%3Dtrue\",\"24x24\":\"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=24&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D24%26noRedirect%3Dtrue\",\"32x32\":\"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=32&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D32%26noRedirect%3Dtrue\",\"48x48\":\"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue\"},\"displayName\":\"Nguyen Phuc Nghi\",\"active\":true},\"components\":[],\"issueTypes\":[{\"self\":\"https://jraproj.atlassian.net/rest/api/3/issuetype/10004\",\"id\":\"10004\",\"description\":\"A task that needs to be done.\",\"iconUrl\":\"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype\",\"name\":\"Task\",\"subtask\":false,\"avatarId\":10318},{\"self\":\"https://jraproj.atlassian.net/rest/api/3/issuetype/10005\",\"id\":\"10005\",\"description\":\"The sub-task of the issue\",\"iconUrl\":\"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype\",\"name\":\"Sub-task\",\"subtask\":true,\"avatarId\":10316},{\"self\":\"https://jraproj.atlassian.net/rest/api/3/issuetype/10003\",\"id\":\"10003\",\"description\":\"Stories track functionality or features expressed as user goals.\",\"iconUrl\":\"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10315&avatarType=issuetype\",\"name\":\"Story\",\"subtask\":false,\"avatarId\":10315},{\"self\":\"https://jraproj.atlassian.net/rest/api/3/issuetype/10006\",\"id\":\"10006\",\"description\":\"A problem which impairs or prevents the functions of the product.\",\"iconUrl\":\"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10303&avatarType=issuetype\",\"name\":\"Bug\",\"subtask\":false,\"avatarId\":10303},{\"self\":\"https://jraproj.atlassian.net/rest/api/3/issuetype/10000\",\"id\":\"10000\",\"description\":\"A big user story that needs to be broken down. Created by Jira Software - do not edit or delete.\",\"iconUrl\":\"https://jraproj.atlassian.net/images/icons/issuetypes/epic.svg\",\"name\":\"Epic\",\"subtask\":false}],\"assigneeType\":\"UNASSIGNED\",\"versions\":[{\"self\":\"https://jraproj.atlassian.net/rest/api/3/version/10001\",\"id\":\"10001\",\"description\":\"test version\",\"name\":\"Ver01\",\"archived\":false,\"released\":false,\"startDate\":\"2019-03-26\",\"releaseDate\":\"2019-03-28\",\"overdue\":true,\"userStartDate\":\"26/Mar/19\",\"userReleaseDate\":\"28/Mar/19\",\"projectId\":10001},{\"self\":\"https://jraproj.atlassian.net/rest/api/3/version/10002\",\"id\":\"10002\",\"name\":\"test ver 2\",\"archived\":false,\"released\":false,\"startDate\":\"2019-03-26\",\"releaseDate\":\"2019-03-27\",\"overdue\":true,\"userStartDate\":\"26/Mar/19\",\"userReleaseDate\":\"27/Mar/19\",\"projectId\":10001}],\"name\":\"Final_Project\",\"roles\":{\"atlassian-addons-project-access\":\"https://jraproj.atlassian.net/rest/api/3/project/10001/role/10003\",\"Administrators\":\"https://jraproj.atlassian.net/rest/api/3/project/10001/role/10002\"},\"avatarUrls\":{\"48x48\":\"https://jraproj.atlassian.net/secure/projectavatar?avatarId=10324\",\"24x24\":\"https://jraproj.atlassian.net/secure/projectavatar?size=small&avatarId=10324\",\"16x16\":\"https://jraproj.atlassian.net/secure/projectavatar?size=xsmall&avatarId=10324\",\"32x32\":\"https://jraproj.atlassian.net/secure/projectavatar?size=medium&avatarId=10324\"},\"projectTypeKey\":\"software\",\"simplified\":false,\"style\":\"classic\",\"isPrivate\":false}\n";
//		JSONObject json = new JSONObject(j);
//		JSONArray versionArray = json.getJSONArray("versions");
//		for (int i = 0; i < versionArray.length(); i++) {
//			JSONObject versionJ = versionArray.getJSONObject(i);
//			JIRAVersion version = new JIRAVersion();
//			version.setStartDate(versionJ.getString("startDate"));
//			version.setReleaseDate(versionJ.getString("releaseDate"));
//			version.setArchived(versionJ.getBoolean("archived"));
//			version.setReleased(versionJ.getBoolean("released"));
//		}

//		String s = "{\"customfield_10021\": [\n" +
//				"            \"com.atlassian.greenhopper.service.sprint.Sprint@438b711e[id=2,rapidViewId=1,state=ACTIVE,name=DFP Sprint 2,goal=,startDate=2019-04-09T15:35:03.136Z,endDate=2019-04-11T20:35:00.000Z,completeDate=<null>,sequence=2]\"\n" +
//				"        ]}";
//		JSONObject jsonObject = new JSONObject(s);
//		Object e = (jsonObject.get("customfield_10021"));
//		if (e.getClass().getName().equals("org.json.JSONArray"))
//		{
//			String key = "customfield_10021";
//			JSONArray j = jsonObject.getJSONArray(key);
//			System.out.println(j.get(0));
//		}


		String f = "com.atlassian.greenhopper.service.sprint.Sprint@558c9d3e[id=2,rapidViewId=1,state=ACTIVE,name=DFP Sprint 2,goal=,startDate=2019-04-09T15:35:03.136Z,endDate=2019-04-11T20:35:00.000Z,completeDate=<null>,sequence=2]\n";
		Pattern p = Pattern.compile("(id=\\d+)");
		Matcher m = p.matcher(f);
		while (m.find()) {
			String x = (m.group(0));
			System.out.println(x.substring(3));
		}

	}
}
