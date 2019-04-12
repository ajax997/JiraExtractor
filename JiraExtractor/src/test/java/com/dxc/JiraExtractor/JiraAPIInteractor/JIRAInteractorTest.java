package com.dxc.JiraExtractor.JiraAPIInteractor;

import com.dxc.JiraExtractor.ConfigStuffs;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProject;
import com.dxc.JiraExtractor.JIRAObjects.JIRAProjectDetail;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:51
 */
public class JIRAInteractorTest {

    @Test
    public void login_true() {
        JIRAInteractor interactor = new JIRAInteractor("https://jiraproj2.atlassian.net");
        assertTrue(interactor.login("nghinguyen2011@yahoo.com.vn", "Lumia1020"));
    }

    @Test
    public void login_false()
    {
        JIRAInteractor interactor = new JIRAInteractor("https://jiraproj2.atlassian.net");
        assertNotEquals(interactor.login("nghinguyen1997@yahoo.com.vn", "Lumia1021"), true);
    }

    @Test
    public void getProjects() {
        ConfigStuffs.urlString = "https://jiraproj2.atlassian.net";
        JIRAInteractor interactor = new JIRAInteractor("https://jiraproj2.atlassian.net");
        ArrayList<JIRAProject> projects = interactor.getProjects();
        assertNotNull(projects);
    }

    @Test
    public void getAllUsers() {
        ConfigStuffs.urlString = "https://jiraproj2.atlassian.net";
        JIRAInteractor interactor = new JIRAInteractor("https://jiraproj2.atlassian.net");
        assertNotNull(interactor.getAllUsers());
    }
}