package com.dxc.JiraExtractor;

import java.util.ArrayList;

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
		
		ArrayList<JIRAProject> objects = new ArrayList<>();
		objects.add(new JIRAProject());
		objects.add(new JIRAProject());
		System.out.print(new Gson().toJson(objects));
		
		
	}
}
