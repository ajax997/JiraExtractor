package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:11
 */
public class JIRAIssueDAO {
    public void addIssue(Connection cnn, JIRAIssueDetail issueDetail) {

        String sql = "insert into issue (idIssue, _key, summary, issuetype, parent, project, fixVersions, assignee, creator, reporter, sprint, self) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(issueDetail.getId()));

            preparedStatement.setString(2, issueDetail.getKey());
            preparedStatement.setString(3, issueDetail.getSummary());
            preparedStatement.setString(4, String.valueOf(issueDetail.getIssueType().getId()));
            //TODO missing ici
            try {
                preparedStatement.setString(5, issueDetail.getParentID());
            } catch (Exception e) {
                preparedStatement.setString(5, null);
            }
            preparedStatement.setString(6, issueDetail.getProject().getId());
            //TODO missing fixVersion
            try {
                preparedStatement.setString(7, issueDetail.getFixVersions());
            } catch (Exception e) {
                preparedStatement.setString(7, null);
            }

            if (issueDetail.getAssignee() != null)
                preparedStatement.setString(8, issueDetail.getAssignee().getAccountId());
            else
                preparedStatement.setString(8, null);

            preparedStatement.setString(9, issueDetail.getCreator().getAccountId());
            preparedStatement.setString(10, issueDetail.getReporter().getAccountId());
            //TODO missing ici
            try {
                preparedStatement.setString(11, issueDetail.getSprintID());
            } catch (Exception e) {
                preparedStatement.setString(11, null);
            }

            preparedStatement.setString(12, issueDetail.getSelf());

            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");

        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("COLLISION ISSUE");
                e.printStackTrace();
            }
        }
        finally {
            try {
                MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 1").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<JIRAIssueDetail> getAllIssueDetail(Connection cnn, int issueID) {
        ArrayList<JIRAIssueDetail> issueDetails = new ArrayList<>();
        String sql = "select * from issue";
        if (issueID != -1)

            sql = "select * from issue where idIssue = " + issueID;

        try {
            toIssueDetails(cnn, issueDetails, sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return issueDetails;
    }

    public ArrayList<JIRAIssueDetail> getAllSubtask(Connection cnn, String parentID) {
        ArrayList<JIRAIssueDetail> issueDetails = new ArrayList<>();

            String sql = "select * from issue where parent = " + parentID;

        try {
            toIssueDetails(cnn, issueDetails, sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return issueDetails;
    }



    public ArrayList<JIRAIssueDetail> getAllIssueByProjectID(Connection cnn, int projectID) {
        ArrayList<JIRAIssueDetail> issueDetails = new ArrayList<>();


        String sql = "select  * from issue where project=" + projectID;
        try {
            toIssueDetails(cnn, issueDetails, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueDetails;
    }


    private boolean checkContain(ArrayList<JIRAIssueDetail> arrayList, JIRAIssueDetail issueDetail) {
        for (JIRAIssueDetail d : arrayList) {
            if (d.getId().equals(issueDetail.getId()))
                return true;


        }
        return false;
    }

    private void toIssueDetails(Connection cnn, ArrayList<JIRAIssueDetail> issueDetails, String sql) throws SQLException {
        PreparedStatement p = cnn.prepareStatement(sql);
        ResultSet resultSet = p.executeQuery();

        while (resultSet.next()) {
            JIRAIssueDetail jiraIssueDetail = new JIRAIssueDetail();
            jiraIssueDetail.setId(String.valueOf(resultSet.getInt("idIssue")));
            jiraIssueDetail.setKey(resultSet.getString("_key"));
            jiraIssueDetail.setSummary(resultSet.getString("summary"));

            jiraIssueDetail.setIssueType(new JIRAIssueTypeDAO().getAllIssueType(MYSQLDAOHelper
                    .getConnection(), resultSet.getInt("issuetype")).get(0));

            jiraIssueDetail.setProject(new JIRAProjectDAO().getAllProject(MYSQLDAOHelper
                    .getConnection(), resultSet.getInt("project")).get(0));

            try {
                jiraIssueDetail.setAssignee(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                        getConnection(), resultSet.getString("assignee")).get(0));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            jiraIssueDetail.setCreator(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                    getConnection(), resultSet.getString("creator")).get(0));

            jiraIssueDetail.setReporter(new JIRAAccountDAO().getAllUser(MYSQLDAOHelper.
                    getConnection(), resultSet.getString("reporter")).get(0));

            try {
                jiraIssueDetail.setSprintID(String.valueOf(resultSet.getInt("sprint")));
            } catch (Exception e) {
                e.getMessage();
            }

            try {
                jiraIssueDetail.setVersion(new JIRAVersionDAO().getVersionById(MYSQLDAOHelper.
                        getConnection(), String.valueOf(resultSet.getInt("fixVersions"))));
            } catch (Exception e) {
                e.getMessage();
            }
            
            try {
                jiraIssueDetail.setParentID(String.valueOf(resultSet.getInt("parent")));
            } catch (Exception e) {
                e.getMessage();
            }


            if (!checkContain(issueDetails, jiraIssueDetail)) {
                issueDetails.add(jiraIssueDetail);
            }

        }
    }
}
