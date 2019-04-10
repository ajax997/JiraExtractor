package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:12
 */
public class JIRAProjectDetailDAO {
    public void addProjectDetail(Connection cnn, JIRAProjectDetail projectDetail) {
        String sql = "insert into projectdetail (idProject, description, `lead`) values (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(projectDetail.getId()));
            preparedStatement.setString(2, projectDetail.getDescription());
            preparedStatement.setString(3, projectDetail.getProjectUser().getKey());
            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JIRAProjectDetail> getAllProjectDetail(Connection cnn) {
        ArrayList<JIRAProjectDetail> projects = new ArrayList<>();
        String sql = "select * from projectdetail";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JIRAProjectDetail projectDetail = new JIRAProjectDetail();
                projectDetail.setId(String.valueOf(resultSet.getInt("idProject")));
                projectDetail.setDescription(resultSet.getString("description"));
                //TODO

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }
}
