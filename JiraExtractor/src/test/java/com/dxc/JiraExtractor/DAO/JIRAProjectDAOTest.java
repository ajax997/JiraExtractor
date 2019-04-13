package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAVersion;
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
public class JIRAProjectDAOTest {

    @Test
    public void addProject() throws SQLException, JSONException {
        String projectJSON = "{\n" +
                "        \"expand\": \"description,lead,issueTypes,url,projectKeys\",\n" +
                "        \"self\": \"https://jraproj.atlassian.net/rest/api/2/project/10001\",\n" +
                "        \"id\": \"10001\",\n" +
                "        \"key\": \"FP\",\n" +
                "        \"name\": \"Final_Project\",\n" +
                "        \"avatarUrls\": {\n" +
                "            \"48x48\": \"https://jraproj.atlassian.net/secure/projectavatar?avatarId=10324\",\n" +
                "            \"24x24\": \"https://jraproj.atlassian.net/secure/projectavatar?size=small&avatarId=10324\",\n" +
                "            \"16x16\": \"https://jraproj.atlassian.net/secure/projectavatar?size=xsmall&avatarId=10324\",\n" +
                "            \"32x32\": \"https://jraproj.atlassian.net/secure/projectavatar?size=medium&avatarId=10324\"\n" +
                "        },\n" +
                "        \"projectTypeKey\": \"software\",\n" +
                "        \"simplified\": false,\n" +
                "        \"style\": \"classic\",\n" +
                "        \"isPrivate\": false\n" +
                "    }";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRAProject project = new POJOFromJson().getJiraProjectFromJson(new JSONObject(projectJSON));
        new JIRAProjectDAO().addProject(MYSQLDAOHelper.getConnection(), project);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from project where idProject = 10001");
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
    public void getAllProject() {
        JIRAProjectDAO dao = new JIRAProjectDAO();
        assertTrue(dao.getAllProject(MYSQLDAOHelper.getConnection(),10001).size()>0);
    }
}