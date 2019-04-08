package com.dxc.JiraExtractor.JIRAObjects;


public class JIRAIssue {
	private int avatarId;

    private String name;

    private String self;

    private String description;

    private String id;

    private String iconUrl;

    private boolean subtask;

    public int getAvatarId ()
    {
        return avatarId;
    }

    public void setAvatarId (int avatarId)
    {
        this.avatarId = avatarId;
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

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIconUrl ()
    {
        return iconUrl;
    }

    public void setIconUrl (String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public boolean getSubtask ()
    {
        return subtask;
    }

    public void setSubtask (boolean subtask)
    {
        this.subtask = subtask;
    }


}
