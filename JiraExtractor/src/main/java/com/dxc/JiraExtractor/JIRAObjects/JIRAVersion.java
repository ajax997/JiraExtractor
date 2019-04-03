package com.dxc.JiraExtractor.JIRAObjects;

public class JIRAVersion {
	private boolean archived;

    private String name;

    private String self;

    private String id;

    private int projectId;

    private boolean released;
    
    private String startDate;
    
    private String releaseDate;
    
    private String description;

    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

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
