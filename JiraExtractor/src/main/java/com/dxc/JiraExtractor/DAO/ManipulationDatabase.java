package com.dxc.JiraExtractor.DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by nguyennghi on 2019-04-11 19:39
 */
public class ManipulationDatabase {
    public void dropTables()
    {
        Connection connection = MYSQLDAOHelper.getConnection();
        try {
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();
            connection.prepareStatement("drop table if exists account").execute();
            connection.prepareStatement("drop table if exists dashboard").execute();
            connection.prepareStatement("drop table if exists issue").execute();
            connection.prepareStatement("drop table if exists issuetype").execute();
            connection.prepareStatement("drop table if exists project").execute();
            connection.prepareStatement("drop table if exists projectdetail").execute();
            connection.prepareStatement("drop table if exists sprint").execute();
            connection.prepareStatement("drop table if exists version").execute();
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTables()
    {
        dropTables();
        Connection connection = MYSQLDAOHelper.getConnection();
        try {
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").execute();

            connection.prepareStatement("CREATE TABLE `account` (\n" +
                    "  `accountId` varchar(45) NOT NULL,\n" +
                    "  `name` varchar(500) DEFAULT NULL,\n" +
                    "  `emailAddress` varchar(45) DEFAULT NULL,\n" +
                    "  `avatarUrl` varchar(1000) DEFAULT NULL,\n" +
                    "  `displayName` varchar(100) DEFAULT NULL,\n" +
                    "  `active` binary(1) DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`accountId`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();

            connection.prepareStatement("CREATE TABLE `dashboard` (\n" +
                    "  `idDashboard` INT NOT NULL,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `_view` text,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idDashboard`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();

            connection.prepareStatement("CREATE TABLE `issue` (\n" +
                    "  `idIssue` INT NOT NULL,\n" +
                    "  `_key` varchar(45) DEFAULT NULL,\n" +
                    "  `summary` varchar(1000) DEFAULT NULL,\n" +
                    "  `issuetype` INT DEFAULT NULL,\n" +
                    "  `parent` INT DEFAULT NULL,\n" +
                    "  `project` INT DEFAULT NULL,\n" +
                    "  `fixVersions` INT DEFAULT NULL,\n" +
                    "  `assignee` varchar(45) DEFAULT NULL,\n" +
                    "  `creator` varchar(45) DEFAULT NULL,\n" +
                    "  `reporter` varchar(45) DEFAULT NULL,\n" +
                    "  `sprint` INT DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idIssue`),\n" +
                    "  KEY `fk_Issue_IssueType_idx` (`issuetype`),\n" +
                    "  KEY `fk_Issue_IssueParent_idx` (`parent`),\n" +
                    "  KEY `fk_Issue_Project_idx` (`project`),\n" +
                    "  KEY `fk_Issue_Version_idx` (`fixVersions`),\n" +
                    "  KEY `fk_Issue_Account_Assignee_idx` (`assignee`),\n" +
                    "  KEY `fk_Issue_Account_Creator_idx` (`creator`),\n" +
                    "  KEY `fk_Issue_Reposter_idx` (`reporter`),\n" +
                    "  KEY `fk_Issue_Sprint_idx` (`sprint`),\n" +
                    "  CONSTRAINT `fk_Issue_Account_Assignee` FOREIGN KEY (`assignee`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_Account_Creator` FOREIGN KEY (`creator`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_IssueParent` FOREIGN KEY (`parent`) REFERENCES `issue` (`idIssue`) ON DELETE SET NULL ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_IssueType` FOREIGN KEY (`issuetype`) REFERENCES `issuetype` (`idIssuetype`) ON DELETE CASCADE ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_Reposter` FOREIGN KEY (`reporter`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_Sprint` FOREIGN KEY (`sprint`) REFERENCES `sprint` (`idSprint`) ON DELETE SET NULL ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Issue_Version` FOREIGN KEY (`fixVersions`) REFERENCES `version` (`idVersion`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();

            connection.prepareStatement("CREATE TABLE `issuetype` (\n" +
                    "  `idIssuetype` INT NOT NULL,\n" +
                    "  `description` varchar(1000) DEFAULT NULL,\n" +
                    "  `iconUrl` varchar(1000) DEFAULT NULL,\n" +
                    "  `name` varchar(500) DEFAULT NULL,\n" +
                    "  `subtask` binary(1) DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idIssuetype`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();

            connection.prepareStatement("CREATE TABLE `project` (\n" +
                    "  `idProject` INT NOT NULL,\n" +
                    "  `_key` varchar(45) CHARACTER SET utf8 DEFAULT NULL,\n" +
                    "  `name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,\n" +
                    "  `avatarUrl` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,\n" +
                    "  `projectTypeKey` varchar(45) CHARACTER SET utf8 DEFAULT NULL,\n" +
                    "  `isPrivate` binary(1) DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idProject`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();


            connection.prepareStatement("CREATE TABLE `projectdetail` (\n" +
                    "  `idProject` INT NOT NULL,\n" +
                    "  `description` varchar(1000) DEFAULT NULL,\n" +
                    "  `lead` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idProject`),\n" +
                    "  KEY `fk_Project_Account_lead_idx` (`lead`),\n" +
                    "  CONSTRAINT `fk_Detail_Project` FOREIGN KEY (`idProject`) REFERENCES `project` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Project_Account_lead` FOREIGN KEY (`lead`) REFERENCES `account` (`accountId`) ON DELETE SET NULL ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n").execute();

            connection.prepareStatement("CREATE TABLE `sprint` (\n" +
                    "  `idSprint` INT NOT NULL,\n" +
                    "  `name` varchar(500) DEFAULT NULL,\n" +
                    "  `state` varchar(45) DEFAULT NULL,\n" +
                    "  `startDate` varchar(45) DEFAULT NULL,\n" +
                    "  `endDate` varchar(45) DEFAULT NULL,\n" +
                    "  `project` INT DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idSprint`),\n" +
                    "  KEY `fk_Sprint_Project_idx` (`project`),\n" +
                    "  CONSTRAINT `fk_Sprint_Project` FOREIGN KEY (`project`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();


            connection.prepareStatement("CREATE TABLE `version` (\n" +
                    "  `idVersion` INT NOT NULL,\n" +
                    "  `description` varchar(1000) DEFAULT NULL,\n" +
                    "  `name` varchar(500) DEFAULT NULL,\n" +
                    "  `archived` binary(1) DEFAULT NULL,\n" +
                    "  `released` binary(1) DEFAULT NULL,\n" +
                    "  `startDate` varchar(45) DEFAULT NULL,\n" +
                    "  `releaseDate` varchar(45) DEFAULT NULL,\n" +
                    "  `projectId` INT DEFAULT NULL,\n" +
                    "  `self` varchar(1000) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idVersion`),\n" +
                    "  KEY `fk_Vesion_Project_idx` (`projectId`),\n" +
                    "  CONSTRAINT `fk_Vesion_Project` FOREIGN KEY (`projectId`) REFERENCES `projectdetail` (`idProject`) ON DELETE CASCADE ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;").execute();

            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").execute();
        }
        catch (SQLException e)
        {
            new ManipulationDatabase().dropTables();
            e.printStackTrace();
        }
    }
}
