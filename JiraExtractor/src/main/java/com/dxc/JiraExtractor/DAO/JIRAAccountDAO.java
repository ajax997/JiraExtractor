package com.dxc.JiraExtractor.DAO;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by nguyennghi on 2019-04-06 22:10
 */
public class JIRAAccountDAO {
    public void addAccount(Connection cnn, JIRAProjectUser user)
    {
        String sql = "INSERT INTO account (accountId, name, emailAddress, avatarUrl, displayName, active, self) values (?, ?, ?, ?, ?, ?, ?) ";
        try{
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setString(1, (user.getAccountId()));
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,"");
            preparedStatement.setString(4, user.getAvatarUrls());
            preparedStatement.setString(5, user.getDisplayName());
            preparedStatement.setBoolean(6, user.getActive());
            preparedStatement.setString(7, user.getSelf());

            preparedStatement.execute();
            System.out.println("INSERT COMPLETE!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<JIRAProjectUser> getAllUser(Connection cnn, String userID)
    {
        ArrayList<JIRAProjectUser> users = null;
        String selectSQL = "Select * from account";
        if(!userID.equals("*"))
            selectSQL = "Select * from account where accountId = "+userID;

        try {
            PreparedStatement p = cnn.prepareStatement(selectSQL);
            users = new ArrayList<>();
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next())
            {
                JIRAProjectUser user = new JIRAProjectUser();
                user.setAccountId(String.valueOf(resultSet.getInt("accountId")));
                user.setName(resultSet.getString("name"));
                user.setAvatarUrls(resultSet.getString("avatarUrl"));
                user.setDisplayName("displayName");
                user.setActive(resultSet.getBoolean("active"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


}
