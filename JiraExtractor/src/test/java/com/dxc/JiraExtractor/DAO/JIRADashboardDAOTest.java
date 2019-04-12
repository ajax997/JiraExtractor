package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRADashboard;
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
 * Created by nguyennghi on 2019-04-12 21:48
 */
public class JIRADashboardDAOTest {

    @Test
    public void addDashboard() throws SQLException, JSONException {
        String dashboardJson = "{\n" +
                "            \"id\": \"10000\",\n" +
                "            \"isFavourite\": false,\n" +
                "            \"name\": \"Default dashboard\",\n" +
                "            \"popularity\": 0,\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/dashboard/10000\",\n" +
                "            \"sharePermissions\": [\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"type\": \"global\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"view\": \"https://jraproj.atlassian.net/secure/Dashboard.jspa?selectPageId=10000\"\n" +
                "        }";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRADashboard dashboard = new POJOFromJson().getDashboardFromJson(new JSONObject(dashboardJson));
        new JIRADashboardDAO().addDashboard(MYSQLDAOHelper.getConnection(), dashboard);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from dashboard where idDashboard = 10000");
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
    public void getAllDashboard() {
        JIRADashboardDAO dashboardDAO = new JIRADashboardDAO();
        assertTrue(dashboardDAO.getAllDashboard(MYSQLDAOHelper.getConnection()).size()>0);
    }
}