package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAIssue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:11
 */
public class JIRAIssueTypeDAO {
    public void addIssue(Connection cnn, JIRAIssue issue) {
        String sql = "insert into issuetype (idIssuetype, description, iconUrl, name, subtask, self) values(?, ?, ?, ?, ?,?) ";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(issue.getId()));
            preparedStatement.setString(2, issue.getDescription());
            preparedStatement.setString(3, issue.getIconUrl());
            preparedStatement.setString(4, issue.getName());
            preparedStatement.setBoolean(5, issue.getSubtask());
            preparedStatement.setString(6, issue.getSelf());
            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JIRAIssue> getAllIssueType(Connection cnn, int issueID) {
        ArrayList<JIRAIssue> issues = new ArrayList<>();
        String sql;
        if (issueID == -1) {
             sql = "select * from issuetype";
        } else {
             sql = "select * from issuetype where idIssuetype = "+issueID;
        }

        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JIRAIssue jiraIssue = new JIRAIssue();
                jiraIssue.setId(String.valueOf(resultSet.getInt("idIssuetype")));
                jiraIssue.setDescription(resultSet.getString("description"));
                jiraIssue.setIconUrl(resultSet.getString("iconUrl"));
                jiraIssue.setName(resultSet.getString("name"));
                jiraIssue.setSubtask(resultSet.getBoolean("subtask"));

                issues.add(jiraIssue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }


}
