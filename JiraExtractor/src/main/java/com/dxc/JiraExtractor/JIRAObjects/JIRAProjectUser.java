package com.dxc.JiraExtractor.JIRAObjects;

public class JIRAProjectUser {
	private String accountId = "-1";

    private String avatarUrls;

    private String displayName;

    private String name;

    private String self;

    private boolean active;

    private String key;

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    public String getAvatarUrls ()
    {
        return avatarUrls;
    }

    public void setAvatarUrls (String avatarUrls)
    {
        this.avatarUrls = avatarUrls;
    }

    public String getDisplayName ()
    {
        return displayName;
    }

    public void setDisplayName (String displayName)
    {
        this.displayName = displayName;
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

    public boolean getActive ()
    {
        return active;
    }

    public void setActive (boolean active)
    {
        this.active = active;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }
}
