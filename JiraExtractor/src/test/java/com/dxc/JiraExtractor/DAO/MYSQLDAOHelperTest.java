package com.dxc.JiraExtractor.DAO;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:49
 */
public class MYSQLDAOHelperTest {

    @Test
    public void getConnection() {
        Connection connection = MYSQLDAOHelper.getConnection();
        assertNotNull(connection);
    }
}