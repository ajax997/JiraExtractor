package com.dxc.JiraExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dxc.JiraExtractor.DAO.MYSQLDAOHelper;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.json.JSONObject;

import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JiraAPIInteractor.JIRAInteractor;
import com.google.gson.Gson;

public class Test {
	public static void main(String[] args)
	{
//		JIRAInteractor interactor = new JIRAInteractor("https://jraproj.atlassian.net");
//		interactor.login("nguyennghi1997@outlook.com", "Lumia1020");
//		
//		System.out.print(interactor.getProjects().size());
		
//		ArrayList<JIRAProject> objects = new ArrayList<>();
//		objects.add(new JIRAProject());
//		objects.add(new JIRAProject());
//		System.out.print(new Gson().toJson(objects));

		Connection connection = new MYSQLDAOHelper().getConnection();

		try {
			int c = 0;
			PreparedStatement preparedStatement = connection.prepareStatement("select * from issuetype");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				c+=1;

			}
			System.out.println(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
}
