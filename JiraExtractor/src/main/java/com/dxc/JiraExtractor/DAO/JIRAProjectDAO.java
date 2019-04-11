package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:11
 */
public class JIRAProjectDAO {
    public void addProject(Connection cnn, JIRAProject project)
    {
        String sql = "insert into project(idProject, _key, name, avatarUrl, projectTypeKey, isPrivate, self) values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(project.getId()));
            preparedStatement.setString(2, project.getKey());
            preparedStatement.setString(3, project.getName());
            preparedStatement.setString(4, project.getAvatarUrl());
            preparedStatement.setString(5, project.getProjectType());
            preparedStatement.setBoolean(6, project.isPrivate());
            preparedStatement.setString(7, project.getUrl());

            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<JIRAProject> getAllProject(Connection cnn, int projectID)
    {
        ArrayList<JIRAProject> projects = new ArrayList<>();
        String sql = "select * from project";
        if (projectID != -1)
            sql = "select * from project where idProject = " + projectID;
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                JIRAProject jiraProject = new JIRAProject();
                jiraProject.setId(String.valueOf(resultSet.getInt("idProject")));
                jiraProject.setKey(resultSet.getString("_key"));
                jiraProject.setName(resultSet.getString("name"));
                jiraProject.setAvatarUrl(resultSet.getString("avatarUrl"));
                jiraProject.setProjectType(resultSet.getString("projectTypeKey"));
                jiraProject.setPrivate(resultSet.getBoolean("isPrivate"));
                jiraProject.setUrl(resultSet.getString("self"));
                projects.add(jiraProject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return projects;
    }
}
