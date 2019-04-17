package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAIssueType;
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
 * Created by nguyennghi on 2019-04-12 21:48
 */
public class JIRAIssueTypeDAOTest {

    @Test
    public void addIssue() throws SQLException, JSONException {
        new ManipulationDatabase().dropTables();
        new ManipulationDatabase().addTables();
        String issueTypeJson = "{\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/issuetype/10003\",\n" +
                "            \"id\": \"10003\",\n" +
                "            \"description\": \"Stories track functionality or features expressed as user goals.\",\n" +
                "            \"iconUrl\": \"https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10315&avatarType=issuetype\",\n" +
                "            \"name\": \"Story\",\n" +
                "            \"subtask\": false,\n" +
                "            \"avatarId\": 10315\n" +
                "        }";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRAIssueType issueType = new POJOFromJson().getIssueTypeFromJson(new JSONObject(issueTypeJson));
        new JIRAIssueTypeDAO().addIssue(MYSQLDAOHelper.getConnection(), issueType);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from issuetype where idIssuetype = 10003");
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
    public void getAllIssueType() throws SQLException, JSONException {
        addIssue();
        assertTrue(new JIRAIssueTypeDAO().getAllIssueType(MYSQLDAOHelper.getConnection(), 10003).size()>0);
    }
}