package com.tester.tester.Util;

import com.tester.tester.Constants.JiraConstants;
import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;

import java.util.List;

public class JiraUtil {

    private static BasicCredentials credentials = new BasicCredentials(JiraConstants.JIRA_USERNAME, JiraConstants.JIRA_PASSWORD);
    private static JiraClient jira = new JiraClient(JiraConstants.JIRA_URL, credentials);

    public static List<Project> getProjects() {
        List<Project> projects = null;
        try {
            projects = jira.getProjects();
        } catch (JiraException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static int getProjectBugTotal(String jql) {
        int total = 0;
        try {
            total =  jira.searchIssues(jql).total;
        } catch (JiraException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static void main(String[] args) throws JiraException {
        BasicCredentials credentials = new BasicCredentials(JiraConstants.JIRA_USERNAME, JiraConstants.JIRA_PASSWORD);
        JiraClient jira = new JiraClient(JiraConstants.JIRA_URL, credentials);
        GreenHopperClient gh = new GreenHopperClient(jira);
        jira.getProjects();
        for (Project a : jira.getProjects()) {
            System.out.println(a.getKey());
            System.out.println(a.getName());
            System.out.println("-------------------------------------");
            break;
        }
        Issue.SearchResult b = jira.searchIssues("project = TEACHING AND issuetype = 故障 ORDER BY 等级 ASC");
        System.out.println(b.total);
    }
}
