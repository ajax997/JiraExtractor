package com.dxc.JiraExtractor.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by nguyennghi on 2019-04-06 21:35
 */
public class MYSQLDAOHelper implements SQLHelper {
    @Override
    public Connection getConnection() {
        Config config = new Config();
        Connection conn = null;
        try {
            Class.forName(config.getDriver());
            String urlString = "jdbc:mysql://" + config.getServer() + ":" + config.getPort() + "/" + config.getDatabase();
            conn = DriverManager.getConnection(urlString, config.getUserId(), config.getPassword());
            //conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/jira_extract","root", "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}
