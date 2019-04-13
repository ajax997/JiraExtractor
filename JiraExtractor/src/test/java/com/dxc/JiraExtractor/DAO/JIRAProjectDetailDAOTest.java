package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectUser;
import com.dxc.JiraExtractor.JIRAObjects.POJOFromJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:49
 */
public class JIRAProjectDetailDAOTest {

    @Test
    public void addProjectDetail() throws SQLException, JSONException {

        String projectDetailJSON = "{\n" +
                "    \"expand\": \"description,lead,issueTypes,url,projectKeys\",\n" +
                "    \"self\": \"https://jraproj.atlassian.net/rest/api/3/project/10001\",\n" +
                "    \"id\": \"10001\",\n" +
                "    \"key\": \"FP\",\n" +
                "    \"description\": \"\",\n" +
                "    \"lead\": {\n" +
                "        \"self\": \"https://jraproj.atlassian.net/rest/api/3/user?accountId=5c99d5e4c377b1118a1b6261\",\n" +
                "        \"key\": \"admin\",\n" +
                "        \"accountId\": \"5c99d5e4c377b1118a1b6261\",\n" +
                "        \"name\": \"admin\",\n" +
                "        \"avatarUrls\": {\n" +
                "            \"16x16\": \"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=16&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D16%26noRedirect%3Dtrue\",\n" +
                "            \"24x24\": \"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=24&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D24%26noRedirect%3Dtrue\",\n" +
                "            \"32x32\": \"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=32&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D32%26noRedirect%3Dtrue\",\n" +
                "            \"48x48\": \"https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue\"\n" +
                "        },\n" +
                "        \"displayName\": \"Nguyen Phuc Nghi\",\n" +
                "        \"active\": true\n" +
                "    },\n" +
                "    \"components\": [],\n" +
                "    \"issueTypes\": [\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10004\",\n" +
                "            \"id\": \"10004\",\n" +
                "            \"description\": \"A task that needs to be done.\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype\",\n" +
                "            \"name\": \"Task\",\n" +
                "            \"subtask\": false,\n" +
                "            \"avatarId\": 10318\n" +
                "        },\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10005\",\n" +
                "            \"id\": \"10005\",\n" +
                "            \"description\": \"The sub-task of the issue\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype\",\n" +
                "            \"name\": \"Sub-task\",\n" +
                "            \"subtask\": true,\n" +
                "            \"avatarId\": 10316\n" +
                "        },\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10003\",\n" +
                "            \"id\": \"10003\",\n" +
                "            \"description\": \"Stories track functionality or features expressed as user goals.\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10315&avatarType=issuetype\",\n" +
                "            \"name\": \"Story\",\n" +
                "            \"subtask\": false,\n" +
                "            \"avatarId\": 10315\n" +
                "        },\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10006\",\n" +
                "            \"id\": \"10006\",\n" +
                "            \"description\": \"A problem which impairs or prevents the functions of the product.\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10303&avatarType=issuetype\",\n" +
                "            \"name\": \"Bug\",\n" +
                "            \"subtask\": false,\n" +
                "            \"avatarId\": 10303\n" +
                "        },\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10000\",\n" +
                "            \"id\": \"10000\",\n" +
                "            \"description\": \"A big user story that needs to be broken down. Created by Jira Software - do not edit or delete.\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/images/icons/issuetypes/epic.svg\",\n" +
                "            \"name\": \"Epic\",\n" +
                "            \"subtask\": false\n" +
                "        }\n" +
                "    ],\n" +
                "    \"assigneeType\": \"UNASSIGNED\",\n" +
                "    \"versions\": [\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/version/10001\",\n" +
                "            \"id\": \"10001\",\n" +
                "            \"description\": \"test version\",\n" +
                "            \"name\": \"Ver01\",\n" +
                "            \"archived\": false,\n" +
                "            \"released\": false,\n" +
                "            \"startDate\": \"2019-03-26\",\n" +
                "            \"releaseDate\": \"2019-03-28\",\n" +
                "            \"overdue\": true,\n" +
                "            \"userStartDate\": \"26/Mar/19\",\n" +
                "            \"userReleaseDate\": \"28/Mar/19\",\n" +
                "            \"projectId\": 10001\n" +
                "        },\n" +
                "        {\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/version/10002\",\n" +
                "            \"id\": \"10002\",\n" +
                "            \"name\": \"test ver 2\",\n" +
                "            \"archived\": false,\n" +
                "            \"released\": false,\n" +
                "            \"startDate\": \"2019-03-26\",\n" +
                "            \"releaseDate\": \"2019-03-27\",\n" +
                "            \"overdue\": true,\n" +
                "            \"userStartDate\": \"26/Mar/19\",\n" +
                "            \"userReleaseDate\": \"27/Mar/19\",\n" +
                "            \"projectId\": 10001\n" +
                "        }\n" +
                "    ],\n" +
                "    \"name\": \"Final_Project\",\n" +
                "    \"roles\": {\n" +
                "        \"atlassian-addons-project-access\": \"https://jraproj.atlassian.net/rest/api/3/project/10001/role/10003\",\n" +
                "        \"Administrators\": \"https://jraproj.atlassian.net/rest/api/3/project/10001/role/10002\"\n" +
                "    },\n" +
                "    \"avatarUrls\": {\n" +
                "        \"48x48\": \"https://jraproj.atlassian.net/secure/projectavatar?avatarId=10324\",\n" +
                "        \"24x24\": \"https://jraproj.atlassian.net/secure/projectavatar?size=small&avatarId=10324\",\n" +
                "        \"16x16\": \"https://jraproj.atlassian.net/secure/projectavatar?size=xsmall&avatarId=10324\",\n" +
                "        \"32x32\": \"https://jraproj.atlassian.net/secure/projectavatar?size=medium&avatarId=10324\"\n" +
                "    },\n" +
                "    \"projectTypeKey\": \"software\",\n" +
                "    \"simplified\": false,\n" +
                "    \"style\": \"classic\",\n" +
                "    \"isPrivate\": false\n" +
                "}";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRAProjectDetail projectDetail = new POJOFromJson().getProjectDetailsFromJson(new JSONObject(projectDetailJSON));
        new JIRAProjectDetailDAO().addProjectDetail(MYSQLDAOHelper.getConnection(), projectDetail);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from projectdetail where idProject=10001");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                sign = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 1").execute();
        assertTrue(sign);
    }

    @Test
    public void getAllProjectDetail() {
        assertTrue(new JIRAProjectDetailDAO().getAllProjectDetail(MYSQLDAOHelper.getConnection()).size()>0);
    }
}