package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRASprint;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:12
 */
public class JIRASprintDAO {
    public void addSprint(Connection cnn, JIRASprint sprint, int project) {
        String sql = "insert into sprint (idSprint, name, state, startDate, endDate, project, self) values (?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, sprint.getId());
            preparedStatement.setString(2, sprint.getName());
            preparedStatement.setString(3, sprint.getState());
            preparedStatement.setString(4, sprint.getStartDate());
            preparedStatement.setString(5, sprint.getEndDate());
            preparedStatement.setInt(6, project);
            preparedStatement.setString(7,"");
            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException)
            {
                System.out.println("CONFLICT");
            }
        }
    }

    public ArrayList<JIRASprint> getAllSprint(Connection cnn, String projectID) {
        ArrayList<JIRASprint> sprints = new ArrayList<>();
        String sql = "select * from sprint where project = " + projectID;
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JIRASprint sprint = new JIRASprint();
                getSprint(resultSet, sprint);
                sprints.add(sprint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sprints;
    }

    private void getSprint(ResultSet resultSet, JIRASprint sprint) throws SQLException {
        sprint.setId(resultSet.getInt("idSprint"));
        sprint.setName(resultSet.getString("name"));
        sprint.setState(resultSet.getString("state"));
        sprint.setStartDate(resultSet.getString("startDate"));
        sprint.setEndDate(resultSet.getString("endDate"));
        sprint.setProjectID(resultSet.getInt("project"));
    }

    public JIRASprint getSprintById(Connection cnn, String sprintId) {
        JIRASprint sprint = new JIRASprint();
        String sql = "select * from sprint where idSprint = " + sprintId;
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                getSprint(resultSet, sprint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sprint;
    }

}
