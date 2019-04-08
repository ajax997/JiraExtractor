package com.dxc.JiraExtractor.JIRAObjects;

import java.util.ArrayList;

public class JIRASprint {
	private ArrayList<JIRAIssueDetail> issues;
	private String state;
	private String startDate;
	private String endDate;
	private String name;
	private int id;
	private String goal;
	private int projectID;

	public ArrayList<JIRAIssueDetail> getIssues() {
		return issues;
	}

	public void setIssues(ArrayList<JIRAIssueDetail> issues) {
		this.issues = issues;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

    public void setProjectID(int projectID) {
		this.projectID = projectID;
    }
    public int getProjectID(int projectID)
	{
		return projectID;
	}
}
