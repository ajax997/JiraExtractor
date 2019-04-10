package com.dxc.JiraExtractor.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by nguyennghi on 2019-04-06 21:35
 */
public class MYSQLDAOHelper {

    static Connection conn = null;
    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        } else {
            Config config = new Config();
            try {
                Class.forName(config.getDriver());
                String urlString = "jdbc:mysql://" + config.getServer() + ":" + config.getPort() + "/" + config.getDatabase();
                conn = DriverManager.getConnection(urlString, config.getUserId(), config.getPassword());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return conn;
        }
    }
}
