package com.dxc.JiraExtractor;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.dxc.JiraExtractor.DAO.Config;
import com.dxc.JiraExtractor.DAO.MYSQLDAOHelper;
import com.google.gson.*;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;

public class Test {
	public static void main(String[] args) throws SQLException {
//		String j = "{\"expand\":\"description,lead,issueTypes,url,projectKeys\",\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/project/10000\",\"id\":\"10000\",\"key\":\"DFP\",\"description\":\"\",\"lead\":{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/user?accountId=5ca1ab71e6f7e8117ffb4288\",\"key\":\"admin\",\"accountId\":\"5ca1ab71e6f7e8117ffb4288\",\"name\":\"admin\",\"avatarUrls\":{\"16x16\":\"https://avatar-cdn.atlassian.com/956c04c9453124cbccf0c2917b9545fd?s=16&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F956c04c9453124cbccf0c2917b9545fd%3Fd%3Dmm%26s%3D16%26noRedirect%3Dtrue\",\"24x24\":\"https://avatar-cdn.atlassian.com/956c04c9453124cbccf0c2917b9545fd?s=24&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F956c04c9453124cbccf0c2917b9545fd%3Fd%3Dmm%26s%3D24%26noRedirect%3Dtrue\",\"32x32\":\"https://avatar-cdn.atlassian.com/956c04c9453124cbccf0c2917b9545fd?s=32&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F956c04c9453124cbccf0c2917b9545fd%3Fd%3Dmm%26s%3D32%26noRedirect%3Dtrue\",\"48x48\":\"https://avatar-cdn.atlassian.com/956c04c9453124cbccf0c2917b9545fd?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F956c04c9453124cbccf0c2917b9545fd%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue\"},\"displayName\":\"Nguyen Nghi\",\"active\":true},\"components\":[],\"issueTypes\":[{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/issuetype/10002\",\"id\":\"10002\",\"description\":\"A task that needs to be done.\",\"iconUrl\":\"https://jiraproj2.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype\",\"name\":\"Task\",\"subtask\":false,\"avatarId\":10318},{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/issuetype/10003\",\"id\":\"10003\",\"description\":\"The sub-task of the issue\",\"iconUrl\":\"https://jiraproj2.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype\",\"name\":\"Sub-task\",\"subtask\":true,\"avatarId\":10316},{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/issuetype/10001\",\"id\":\"10001\",\"description\":\"Stories track functionality or features expressed as user goals.\",\"iconUrl\":\"https://jiraproj2.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10315&avatarType=issuetype\",\"name\":\"Story\",\"subtask\":false,\"avatarId\":10315},{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/issuetype/10004\",\"id\":\"10004\",\"description\":\"A problem which impairs or prevents the functions of the product.\",\"iconUrl\":\"https://jiraproj2.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10303&avatarType=issuetype\",\"name\":\"Bug\",\"subtask\":false,\"avatarId\":10303},{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/issuetype/10000\",\"id\":\"10000\",\"description\":\"A big user story that needs to be broken down. Created by Jira Software - do not edit or delete.\",\"iconUrl\":\"https://jiraproj2.atlassian.net/images/icons/issuetypes/epic.svg\",\"name\":\"Epic\",\"subtask\":false}],\"assigneeType\":\"UNASSIGNED\",\"versions\":[{\"self\":\"https://jiraproj2.atlassian.net/rest/api/3/version/10000\",\"id\":\"10000\",\"name\":\"JIRA Extractor Core Features\",\"archived\":false,\"released\":false,\"startDate\":\"2019-04-01\",\"releaseDate\":\"2019-04-12\",\"overdue\":false,\"userStartDate\":\"01/Apr/19\",\"userReleaseDate\":\"12/Apr/19\",\"projectId\":10000}],\"name\":\"DXC Final Project\",\"roles\":{\"atlassian-addons-project-access\":\"https://jiraproj2.atlassian.net/rest/api/3/project/10000/role/10003\",\"Administrators\":\"https://jiraproj2.atlassian.net/rest/api/3/project/10000/role/10002\"},\"avatarUrls\":{\"48x48\":\"https://jiraproj2.atlassian.net/secure/projectavatar?avatarId=10324\",\"24x24\":\"https://jiraproj2.atlassian.net/secure/projectavatar?size=small&avatarId=10324\",\"16x16\":\"https://jiraproj2.atlassian.net/secure/projectavatar?size=xsmall&avatarId=10324\",\"32x32\":\"https://jiraproj2.atlassian.net/secure/projectavatar?size=medium&avatarId=10324\"},\"projectTypeKey\":\"software\",\"simplified\":false,\"style\":\"classic\",\"isPrivate\":false}";
//
//		System.out.println(j);
//
//
//		JsonParser parser = new JsonParser();
//
//		JSONObject jsonObject = new JSONObject(j);
//
//		JSONArray jsonArray = jsonObject.getJSONArray("versions");
//
//		JSONObject jsonObject1 = jsonArray.getJSONObject(0);
//		System.out.println(jsonObject1.getString("startDate"));
//		System.out.println(jsonObject1.getBoolean("released"));


//		JsonElement jsonTree = parser.parse(j);
//		JsonObject jsonObject = jsonTree.getAsJsonObject();
//		String memberName;
//		JsonArray jsonElements = jsonObject.getAsJsonArray("versions");
//		JsonObject jsonObject1 = jsonElements.get(0).getAsJsonObject();
//
//		System.out.println(jsonObject1.get("startDate").toString());

//		InputStream f = Config.class.getClassLoader().getResourceAsStream("db_JIRAExtract.sql");
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(f));
//		Scanner scanner = new Scanner(bufferedReader);
//		StringBuilder stringBuilder = new StringBuilder();
//		while (scanner.hasNextLine())
//		{
//			String sql = scanner.nextLine();
//			if (!sql.equals("--")) {
//				PreparedStatement preparedStatement = null;
//				try {
//					preparedStatement = MYSQLDAOHelper.getConnection().prepareStatement(scanner.nextLine());
//					preparedStatement.execute();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					System.out.println(sql);
//				}
//
//			}
//		}
//
//		System.out.println(stringBuilder.toString());

		String sql = "CREATE DATABASE IF NOT EXISTS `jira_extract`  " +
				"DEFAULT CHARACTER SET latin1;" +
				"USE `jira_extract`;" +

				"DROP TABLE IF EXISTS `account`;" +
				"CREATE TABLE `account` (" +
				"  `accountId` varchar(45) NOT NULL," +
				"  `name` varchar(500) DEFAULT NULL," +
				"  `emailAddress` varchar(45) DEFAULT NULL," +
				"  `avatarUrl` varchar(1000) DEFAULT NULL," +
				"  `displayName` varchar(100) DEFAULT NULL," +
				"  `active` binary(1) DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`accountId`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `account` WRITE;" +
				"/*!40000 ALTER TABLE `account` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `account` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `dashboard`;" +
				"/*!40101 SET @saved_cs_client     = @@character_set_client */;" +
				"/*!40101 SET character_set_client = utf8 */;" +
				"CREATE TABLE `dashboard` (" +
				"  `idDashboard` INT NOT NULL," +
				"  `name` varchar(45) DEFAULT NULL," +
				"  `_view` text," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idDashboard`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `dashboard` WRITE;" +
				"/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `issue`;" +
				"CREATE TABLE `issue` (" +
				"  `idIssue` INT NOT NULL," +
				"  `_key` varchar(45) DEFAULT NULL," +
				"  `summary` varchar(1000) DEFAULT NULL," +
				"  `issuetype` INT DEFAULT NULL," +
				"  `parent` INT DEFAULT NULL," +
				"  `project` INT DEFAULT NULL," +
				"  `fixVersions` INT DEFAULT NULL," +
				"  `assignee` varchar(45) DEFAULT NULL," +
				"  `creator` varchar(45) DEFAULT NULL," +
				"  `reporter` varchar(45) DEFAULT NULL," +
				"  `sprint` INT DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idIssue`)," +
				"  KEY `fk_Issue_IssueType_idx` (`issuetype`)," +
				"  KEY `fk_Issue_IssueParent_idx` (`parent`)," +
				"  KEY `fk_Issue_Project_idx` (`project`)," +
				"  KEY `fk_Issue_Version_idx` (`fixVersions`)," +
				"  KEY `fk_Issue_Account_Assignee_idx` (`assignee`)," +
				"  KEY `fk_Issue_Account_Creator_idx` (`creator`)," +
				"  KEY `fk_Issue_Reposter_idx` (`reporter`)," +
				"  KEY `fk_Issue_Sprint_idx` (`sprint`)," +
				"  CONSTRAINT `fk_Issue_Account_Assignee` FOREIGN KEY (`assignee`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_Account_Creator` FOREIGN KEY (`creator`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_IssueParent` FOREIGN KEY (`parent`) REFERENCES `issue` (`idIssue`) ON DELETE SET NULL ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_IssueType` FOREIGN KEY (`issuetype`) REFERENCES `issuetype` (`idIssuetype`) ON DELETE CASCADE ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_Reposter` FOREIGN KEY (`reporter`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_Sprint` FOREIGN KEY (`sprint`) REFERENCES `sprint` (`idSprint`) ON DELETE SET NULL ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Issue_Version` FOREIGN KEY (`fixVersions`) REFERENCES `version` (`idVersion`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `issue` WRITE;" +
				"/*!40000 ALTER TABLE `issue` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `issue` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `issuetype`;" +

				"CREATE TABLE `issuetype` (" +
				"  `idIssuetype` INT NOT NULL," +
				"  `description` varchar(1000) DEFAULT NULL," +
				"  `iconUrl` varchar(1000) DEFAULT NULL," +
				"  `name` varchar(45) DEFAULT NULL," +
				"  `subtask` binary(1) DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idIssuetype`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `issuetype` WRITE;" +
				"/*!40000 ALTER TABLE `issuetype` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `issuetype` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `project`;" +
				"/*!40101 SET @saved_cs_client     = @@character_set_client */;" +
				"/*!40101 SET character_set_client = utf8 */;" +
				"CREATE TABLE `project` (" +
				"  `idProject` INT NOT NULL," +
				"  `_key` varchar(45) CHARACTER SET latin1 DEFAULT NULL," +
				"  `name` varchar(100) CHARACTER SET latin1 DEFAULT NULL," +
				"  `avatarUrl` varchar(1000) CHARACTER SET latin1 DEFAULT NULL," +
				"  `projectTypeKey` varchar(45) CHARACTER SET latin1 DEFAULT NULL," +
				"  `isPrivate` binary(1) DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idProject`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
				"/*!40101 SET character_set_client = @saved_cs_client */;" +

				"LOCK TABLES `project` WRITE;" +
				"/*!40000 ALTER TABLE `project` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `project` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `projectdetail`;" +
				"/*!40101 SET @saved_cs_client     = @@character_set_client */;" +
				"/*!40101 SET character_set_client = utf8 */;" +
				"CREATE TABLE `projectdetail` (" +
				"  `idProject` INT NOT NULL," +
				"  `description` varchar(1000) DEFAULT NULL," +
				"  `lead` varchar(45) DEFAULT NULL," +
				"  PRIMARY KEY (`idProject`)," +
				"  KEY `fk_Project_Account_lead_idx` (`lead`)," +
				"  CONSTRAINT `fk_Detail_Project` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION," +
				"  CONSTRAINT `fk_Project_Account_lead` FOREIGN KEY (`lead`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `projectdetail` WRITE;" +
				"/*!40000 ALTER TABLE `projectdetail` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `projectdetail` ENABLE KEYS */;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `sprint`;" +
				"CREATE TABLE `sprint` (" +
				"  `idSprint` INT NOT NULL," +
				"  `name` varchar(500) DEFAULT NULL," +
				"  `state` varchar(45) DEFAULT NULL," +
				"  `startDate` varchar(45) DEFAULT NULL," +
				"  `endDate` varchar(45) DEFAULT NULL," +
				"  `project` INT DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idSprint`)," +
				"  KEY `fk_Sprint_Project_idx` (`project`)," +
				"  CONSTRAINT `fk_Sprint_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `sprint` WRITE;" +
				"UNLOCK TABLES;" +

				"DROP TABLE IF EXISTS `version`;" +

				"CREATE TABLE `version` (" +
				"  `idVersion` INT NOT NULL," +
				"  `description` varchar(1000) DEFAULT NULL," +
				"  `name` varchar(500) DEFAULT NULL," +
				"  `archived` binary(1) DEFAULT NULL," +
				"  `released` binary(1) DEFAULT NULL," +
				"  `startDate` varchar(45) DEFAULT NULL," +
				"  `releaseDate` varchar(45) DEFAULT NULL," +
				"  `projectId` INT DEFAULT NULL," +
				"  `self` varchar(1000) DEFAULT NULL," +
				"  PRIMARY KEY (`idVersion`)," +
				"  KEY `fk_Vesion_Project_idx` (`projectId`)," +
				"  CONSTRAINT `fk_Vesion_Project` FOREIGN KEY (`projectId`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;" +

				"LOCK TABLES `version` WRITE;" +
				"/*!40000 ALTER TABLE `version` DISABLE KEYS */;" +
				"/*!40000 ALTER TABLE `version` ENABLE KEYS */;" +
				"UNLOCK TABLES;";


		PreparedStatement p = MYSQLDAOHelper.getConnection().prepareStatement(sql);
		p.execute();
	}
}
