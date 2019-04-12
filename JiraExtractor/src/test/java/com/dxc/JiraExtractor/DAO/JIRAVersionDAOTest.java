package com.dxc.JiraExtractor.DAO;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.dxc.JiraExtractor.JIRAObjects.JIRAVersion;
import com.dxc.JiraExtractor.JIRAObjects.POJOFromJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:49
 */
public class JIRAVersionDAOTest {

    @Test
    public void addVersion() throws JSONException, SQLException {
        String versionJson = "{\n" +
                "            \"self\": \"https://jraproj.atlassian.net/rest/api/3/version/10001\",\n" +
                "            \"id\": \"10001\",\n" +
                "            \"description\": \"test version\",\n" +
                "            \"name\": \"Ver01\",\n" +
                "            \"archived\": false,\n" +
                "            \"released\": false,\n" +
                "            \"startDate\": \"2019-03-26\",\n" +
                "            \"releaseDate\": \"2019-03-28\",\n" +
                "            \"overdue\": true,\n" +
                "            \"userStartDate\": \"26/Mar/19\",\n" +
                "            \"userReleaseDate\": \"28/Mar/19\",\n" +
                "            \"projectId\": 10001\n" +
                "        }";

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
        JIRAVersion version = new POJOFromJson().getVersionFromJson(new JSONObject(versionJson));
        new JIRAVersionDAO().addVersion(MYSQLDAOHelper.getConnection(), version);

        boolean sign = false;
        try {
            PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement("select * from version where idVersion = 10001");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                sign = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        MYSQLDAOHelper.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 1").execute();
        assertTrue(sign);
    }

    @Test
    public void getAllVersion() {
        JIRAVersionDAO j = new JIRAVersionDAO();
        assertTrue(j.getAllVersion(MYSQLDAOHelper.getConnection(), "10001").size()>0);
    }
}