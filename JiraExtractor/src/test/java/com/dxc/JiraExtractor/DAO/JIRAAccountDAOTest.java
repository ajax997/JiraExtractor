package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRADashboard;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectUser;
import com.dxc.JiraExtractor.JIRAObjects.POJOFromJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:48
 */
public class JIRAAccountDAOTest {

    @Test
    public void addAccount() throws SQLException, JSONException {
        String accountJson = "{\n" +
                "            \"self\": \"https://jiraproj2.atlassian.net/rest/api/3/user?accountId=5c9498b3fbf7532d14b52b6f\",\n" +
                "            \"name\": \"daothimy46\",\n" +
                "            \"key\": \"daothimy46\",\n" +
                "            \"accountId\": \"5c9498b3fbf7532d14b52b6f\",\n" +
                "            \"emailAddress\": \"daothimy46@gmail.com\",\n" +
                "            \"avatarUrls\": {\n" +
                "                \"48x48\": \"https://avatar-cdn.atlassian.com/b3effea122436c549373a3c798eaa4a6?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2Fb3effea122436c549373a3c798eaa4a6%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue\",\n" +
                "                \"24x24\": \"https://avatar-cdn.atlassian.com/b3effea122436c549373a3c798eaa4a6?s=24&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2Fb3effea122436c549373a3c798eaa4a6%3Fd%3Dmm%26s%3D24%26noRedirect%3Dtrue\",\n" +
                "                \"16x16\": \"https://avatar-cdn.atlassian.com/b3effea122436c549373a3c798eaa4a6?s=16&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2Fb3effea122436c549373a3c798eaa4a6%3Fd%3Dmm%26s%3D16%26noRedirect%3Dtrue\",\n" +
                "                \"32x32\": \"https://avatar-cdn.atlassian.com/b3effea122436c549373a3c798eaa4a6?s=32&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2Fb3effea122436c549373a3c798eaa4a6%3Fd%3Dmm%26s%3D32%26noRedirect%3Dtrue\"\n" +
                "            },\n" +
                "            \"displayName\": \"Mỹ Đào Thị\",\n" +
                "            \"active\": true,\n" +
                "            \"timeZone\": \"Asia/Saigon\",\n" +
                "            \"accountType\": \"atlassian\"\n" +
                "        }";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRAProjectUser user = new POJOFromJson().getProjectUserFromJson(new JSONObject(accountJson));
        new JIRAAccountDAO().addAccount(MYSQLDAOHelper.getConnection(), user);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from account where accountId = \"5c9498b3fbf7532d14b52b6f\"");
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
    public void getAllUser() {
        JIRAAccountDAO accountDAO = new JIRAAccountDAO();
        ArrayList<JIRAProjectUser> projectUsers = accountDAO.getAllUser(MYSQLDAOHelper.getConnection(),"*");
        assertTrue(projectUsers.size()>0);
    }
}