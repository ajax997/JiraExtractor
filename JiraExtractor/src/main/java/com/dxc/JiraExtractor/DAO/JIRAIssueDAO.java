package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:11
 */
public class JIRAIssueDAO {
    public void addIssue(Connection cnn, JIRAIssueDetail issueDetail) {
        String sql = "insert into issue (idIssue, _key, summary, issuetype, parent, project, fixVersions, assignee, creator, reporter, sprint, self) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(issueDetail.getId()));

            System.out.println("ISSUE ID" + Integer.parseInt(issueDetail.getId()));

            preparedStatement.setString(2, issueDetail.getKey());
            preparedStatement.setString(3, issueDetail.getSummary());
            preparedStatement.setString(4, String.valueOf(issueDetail.getIssueType().getId()));
            //TODO missing ici
            preparedStatement.setString(5, "-1");
            preparedStatement.setString(6, issueDetail.getProject().getId());
            //TODO missing fixVersion
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, issueDetail.getAssignee().getAccountId());
            preparedStatement.setString(9, issueDetail.getCreator().getAccountId());
            preparedStatement.setString(10, issueDetail.getReporter().getAccountId());
            //TODO missing ici
            preparedStatement.setString(11, "-1");
            preparedStatement.setString(12, "");

            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JIRAIssueDetail> getAllIssueDetail(Connection cnn) {
        ArrayList<JIRAIssueDetail> issueDetails = new ArrayList<>();
        String sql = "select * from issue";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                JIRAIssueDetail jiraIssueDetail = new JIRAIssueDetail();
                jiraIssueDetail.setId(String.valueOf(resultSet.getInt("idIssue")));
                jiraIssueDetail.setKey(resultSet.getString("key"));
                jiraIssueDetail.setSummary(resultSet.getString("summary"));

                jiraIssueDetail.setIssueType(new JIRAIssueTypeDAO().getAllIssueType(MYSQLDAOHelper
                        .getConnection(), resultSet.getInt("issuetype")).get(0));

                jiraIssueDetail.setProject(new JIRAProjectDAO().getAllProject( MYSQLDAOHelper
                        .getConnection(), resultSet.getInt("project")).get(0));

                jiraIssueDetail.setAssignee(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                        getConnection(), resultSet.getString("assignee")).get(0));

                jiraIssueDetail.setAssignee(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                        getConnection(), resultSet.getString("creator")).get(0));

                jiraIssueDetail.setAssignee(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                        getConnection(), resultSet.getString("reporter")).get(0));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return issueDetails;
    }

}
