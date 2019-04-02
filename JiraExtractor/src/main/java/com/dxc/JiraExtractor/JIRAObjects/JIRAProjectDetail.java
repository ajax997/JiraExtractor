package com.dxc.JiraExtractor.JIRAObjects;

import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.Version;

public class JIRAProjectDetail {
	private String url;
	private String id;
	private String key;
	private String description;
	private JIRAProjectUser projectUser;
	private ArrayList<JIRAIssue> issues;
	private String assigneeType;
	private ArrayList<JIRAVersion> versions;
	private String name;
	private String avatarUrl;
	private String projectType;
	private boolean isPrivate;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public JIRAProjectUser getProjectUser() {
		return projectUser;
	}
	public void setProjectUser(JIRAProjectUser projectUser) {
		this.projectUser = projectUser;
	}
	public ArrayList<JIRAIssue> getIssues() {
		return issues;
	}
	public void setIssues(ArrayList<JIRAIssue> issues) {
		this.issues = issues;
	}
	public String getAssigneeType() {
		return assigneeType;
	}
	public void setAssigneeType(String assigneeType) {
		this.assigneeType = assigneeType;
	}
	public ArrayList<JIRAVersion> getVersions() {
		return versions;
	}
	public void setVersions(ArrayList<JIRAVersion> versions) {
		this.versions = versions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
}
