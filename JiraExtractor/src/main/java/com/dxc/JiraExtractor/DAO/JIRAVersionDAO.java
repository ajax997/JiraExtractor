package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAVersion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:12
 */
public class JIRAVersionDAO {
    public void addVersion(Connection cnn, JIRAVersion version) {
        String sql = "insert into version (idVersion=?, description=?, name=?, archived=?, released=?, startDate=?, releaseDate=?, projectId=?) ";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(version.getId()));
            preparedStatement.setString(2, version.getDescription());
            preparedStatement.setString(3, version.getName());
            preparedStatement.setBoolean(4, version.getArchived());
            preparedStatement.setBoolean(5, version.getReleased());
            preparedStatement.setString(6, version.getStartDate());
            preparedStatement.setString(7, version.getReleaseDate());
            preparedStatement.setInt(8, version.getProjectId());
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JIRAVersion> getAllVersion(Connection cnn)
    {
        ArrayList<JIRAVersion> versions = new ArrayList<>();
        String sql = "select * from version";
        try{
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                JIRAVersion  version = new JIRAVersion();
                version.setId(String.valueOf(resultSet.getInt("idVersion")));
                version.setDescription(resultSet.getString("description"));
                version.setName(resultSet.getString("name"));
                version.setArchived(resultSet.getBoolean("archived"));
                version.setReleased(resultSet.getBoolean("released"));
                version.setStartDate(resultSet.getString("startDate"));
                version.setReleaseDate(resultSet.getString("releaseDate"));
                version.setProjectId(Integer.parseInt(resultSet.getString("projectId")));
                versions.add(version);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return versions;
    }
}
