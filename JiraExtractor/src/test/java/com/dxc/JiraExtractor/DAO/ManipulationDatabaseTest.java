package com.dxc.JiraExtractor.DAO;

import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:50
 */
public class ManipulationDatabaseTest {

    @Test
    public void dropTables() {
        boolean sign = false;
        new ManipulationDatabase().dropTables();
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("SELECT COUNT(*)\n" +
                    "FROM information_schema.tables\n" +
                    "WHERE table_schema = 'jira_extract';");
            ResultSet r = p.executeQuery();
            while (r.next())
            {
                if(r.getInt("COUNT(*)") == 0)
                    sign = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    assertTrue(sign);
    }

    @Test
    public void addTables() {
        boolean sign = false;
        new ManipulationDatabase().addTables();
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("SELECT COUNT(*)\n" +
                    "FROM information_schema.tables\n" +
                    "WHERE table_schema = 'jira_extract';");
            ResultSet r = p.executeQuery();
            while (r.next())
            {
                if(r.getInt("COUNT(*)") == 8)
                    sign = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(sign);
    }
}