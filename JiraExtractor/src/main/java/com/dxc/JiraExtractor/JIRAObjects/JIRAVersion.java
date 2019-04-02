package com.dxc.JiraExtractor.JIRAObjects;

public class JIRAVersion {
	private boolean archived;

    private String name;

    private String self;

    private String id;

    private int projectId;

    private boolean released;

    public boolean getArchived ()
    {
        return archived;
    }

    public void setArchived (boolean archived)
    {
        this.archived = archived;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSelf ()
    {
        return self;
    }

    public void setSelf (String self)
    {
        this.self = self;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public int getProjectId ()
    {
        return projectId;
    }

    public void setProjectId (int projectId)
    {
        this.projectId = projectId;
    }

    public boolean getReleased ()
    {
        return released;
    }

    public void setReleased (boolean released)
    {
        this.released = released;
    }

}
