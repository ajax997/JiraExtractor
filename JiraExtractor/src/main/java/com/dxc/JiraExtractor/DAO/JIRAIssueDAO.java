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
    public void addIssue(Connection cnn, JIRAIssueDetail issueDetail)
    {
        String sql = "insert into issue(idIssue=?, key=?, summary=?, issuetype=?, parent=?, project=?, fixVersions=?, assignee=?, creator=?, reporter=?, sprint=?)";

                try{
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(issueDetail.getId()));
            preparedStatement.setString(2,issueDetail.getKey());
            preparedStatement.setString(3, issueDetail.getSummary());
            preparedStatement.setString(4, String.valueOf(issueDetail.getIssueType()));
            //TODO missing ici
            preparedStatement.setString(5, "");
            preparedStatement.setString(6, issueDetail.getProject().getId());
            //TODO missing fixVersion
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, issueDetail.getAssignee().getAccountId());
            preparedStatement.setString(9, issueDetail.getCreator().getAccountId());
            preparedStatement.setString(10, issueDetail.getReporter().getAccountId());
            //TODO missing ici
            preparedStatement.setString(11, "");

            preparedStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<JIRAIssueDetail> getAllIssueDetail(Connection cnn)
    {
        ArrayList<JIRAIssueDetail> issueDetails = new ArrayList<>();
        String sql = "";
        try{
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                JIRAIssueDetail jiraIssueDetail = new JIRAIssueDetail();
                jiraIssueDetail.setId(String.valueOf(resultSet.getInt("idIssue")));
                jiraIssueDetail.setKey(resultSet.getString("key"));
                jiraIssueDetail.setSummary(resultSet.getString("summary"));
                //TODO
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return issueDetails;
    }



}
