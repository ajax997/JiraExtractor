package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRADashboard;
import com.dxc.JiraExtractor.JiraAPIInteractor.RequestType;
import com.dxc.JiraExtractor.JiraAPIInteractor.SendRequest;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:10
 */
public class JIRADashboardDAO {
    public void addDashboard(Connection cnn, JIRADashboard dashboard) {
        String sql = "insert  into dashboard(idDashboard, name, _view, self) values(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(dashboard.getId()));
            preparedStatement.setString(2, dashboard.getName());
            preparedStatement.setString(3, dashboard.getView());
            preparedStatement.setString(4, dashboard.getUrl());
            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("CONFLICT");
            }
        }
    }

    public ArrayList<JIRADashboard> getAllDashboard(Connection cnn) {
        ArrayList<JIRADashboard> dashboards = new ArrayList<>();
        String sql = "select * from dashboard";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                JIRADashboard jiraDashboard = new JIRADashboard();
                jiraDashboard.setName(resultSet.getString("name"));
                jiraDashboard.setId(String.valueOf(resultSet.getInt("idDashboard")));
                jiraDashboard.setView(resultSet.getString("_view"));
                jiraDashboard.setUrl(resultSet.getString("self"));
                dashboards.add(jiraDashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dashboards;
    }

    public String getDashboardView(Connection cnn ,String dashboardID)
    {
        for (JIRADashboard d :getAllDashboard(cnn))
        {
            if (d.getId().equals(dashboardID))
                return SendRequest.sendRequest(d.getView(), RequestType.GET);
        }
       return "";
    }
}
