package com.dxc.JiraExtractor.JIRAObjects;

public class JIRAIssueDetail {
	private String id;
	private String key;
	private JIRAIssue issueType;
	private JIRAProject project;
	private JIRAProjectUser reporter;
	private JIRAProjectUser creator;
	private JIRAProjectUser assignee;
	private String time;
	private String summary;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public JIRAIssue getIssueType() {
		return issueType;
	}
	public void setIssueType(JIRAIssue issueType) {
		this.issueType = issueType;
	}
	public JIRAProject getProject() {
		return project;
	}
	public void setProject(JIRAProject project) {
		this.project = project;
	}
	public JIRAProjectUser getReporter() {
		return reporter;
	}
	public void setReporter(JIRAProjectUser reporter) {
		this.reporter = reporter;
	}
	public JIRAProjectUser getCreator() {
		return creator;
	}
	public void setCreator(JIRAProjectUser creator) {
		this.creator = creator;
	}
	public JIRAProjectUser getAssignee() {
		return assignee;
	}
	public void setAssignee(JIRAProjectUser assignee) {
		this.assignee = assignee;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	} 
}