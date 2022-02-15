package com.tester.tester.Util;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import com.tester.tester.Constants.ContentConstants;
import com.tester.tester.Constants.JenkinsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class JenkinsUtil {

    private JenkinsUtil() {
    }

    private static final String JENKINS_URL = JenkinsConstants.LOCAL_JENKINS_URL;
    private static final String JENKINS_USERNAME = JenkinsConstants.JENKINS_USERNAME;
    private static final String JENKINS_PASSWORD = JenkinsConstants.JENKINS_PASSWORD;
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    public static JenkinsHttpClient getJenkinsClient() {

        JenkinsHttpClient jenkinsHttpClient = null;
        try {
            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return jenkinsHttpClient;
    }

    private static JenkinsServer connection() {

        JenkinsServer jenkinsServer = null;

        try {
            jenkinsServer = new JenkinsServer(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return jenkinsServer;
    }

    private static JenkinsServer connection(String url, String username, String password) {

        JenkinsServer jenkinsServer = null;

        try {
            jenkinsServer = new JenkinsServer(new URI(url), username, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return jenkinsServer;
    }

    private static Map<String, Job> getJenkinsJob(JenkinsServer jenkinsServer) {

        Map<String, Job> jonMap = null;
        try {
            jonMap = jenkinsServer.getJobs();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jonMap;
    }

    private static JobWithDetails getJob(JenkinsServer jenkinsServer, String JobName) {

        JobWithDetails job = null;
        try {
            job = jenkinsServer.getJob(JobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return job;
    }

    public static JobWithDetails getJobDetail(JenkinsServer jenkinsServer, String jobName) {
        JobWithDetails jobDetails = null;
        try {
            jobDetails = getJenkinsJob(jenkinsServer).get(jobName).details();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobDetails;
    }

    public static void buildJob(JenkinsServer jenkinsServer, String jobName) {

        try {
            jenkinsServer.getJob(jobName).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buildJob(JenkinsServer jenkinsServer, String jobName, Map<String, String> params) {

        try {
            jenkinsServer.getJob(jobName).build(params, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BuildWithDetails getLastBuildDetails(JenkinsServer jenkinsServer, String jobName) {
        BuildWithDetails buildWithDetails = null;
        try {
            buildWithDetails = getJob(jenkinsServer, jobName).getLastBuild().details();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildWithDetails;
    }

    public static boolean isBuildFinish(JenkinsServer jenkinsServer, String jobName) {

        BuildWithDetails buildWithDetails = getLastBuildDetails(jenkinsServer, jobName);

        if(buildWithDetails.getResult() != null) {
            return true;
        }
        return false;
    }

    private static Build getJobInfoByNumber(JenkinsServer jenkinsServer, String jobName, int number) {
       return getJob(jenkinsServer, jobName).getBuildByNumber(number);
    }

    private static BuildResult getBuildResult(JenkinsServer jenkinsServer, String jobName) {
        BuildWithDetails buildWithDetails = getLastBuildDetails(jenkinsServer, jobName);
        return buildWithDetails.getResult();
    }

    private static int getBuildNumber(JenkinsServer jenkinsServer, String jobName) {
        BuildWithDetails buildWithDetails = getLastBuildDetails(jenkinsServer, jobName);
        return buildWithDetails.getNumber();
    }

    private static Map<String, String> getBuildParameters(JenkinsServer jenkinsServer, String jobName) {
        BuildWithDetails buildWithDetails = getLastBuildDetails(jenkinsServer, jobName);
        return buildWithDetails.getParameters();
    }

    private static String getBuildConsoleOutput(JenkinsServer jenkinsServer, String jobName) throws IOException {
        BuildWithDetails buildWithDetails = getLastBuildDetails(jenkinsServer, jobName);
        return buildWithDetails.getConsoleOutputText();
    }

    public static void stopBuildJob(JenkinsServer jenkinsServer, String jobName) {
        Build build = null;
        try {
            build = jenkinsServer.getJob(jobName).getLastBuild();
            build.Stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJob(JenkinsServer jenkinsServer, String jobName) {
        try {
            jenkinsServer.deleteJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disableJob(JenkinsServer jenkinsServer, String jobName) {
        try {
            jenkinsServer.disableJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enableJob(JenkinsServer jenkinsServer, String jobName) {
        try {
            jenkinsServer.enableJob(jobName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int triggerJenkinsAndResult(String url, String username, String password, String jobName, Map<String, String> params) {
        JenkinsServer jenkinsServer = connection(url, username, password);
        buildJob(jenkinsServer, jobName, params);
        return getRunResult(jenkinsServer, jobName);
    }

    public static int triggerJenkinsAndResult(String jobName, Map<String, String> params) {
        JenkinsServer jenkinsServer = connection();
        buildJob(jenkinsServer,jobName, params);
        return getRunResult(jenkinsServer, jobName);
    }

    public static void triggerJenkins(String jobName, Map<String, String> params) {
        JenkinsServer jenkinsServer = connection();
        buildJob(jenkinsServer,jobName, params);
    }

    private static int getRunResult(JenkinsServer jenkinsServer, String jobName) {
        int maxTimes = 30;
        int times = 0;
        connection();
        while (!isBuildFinish(jenkinsServer, jobName)) {
            if (times < maxTimes) {
                try {
                    Thread.sleep(60 * ContentConstants.TIME);
                    times += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return -1;
                }
            }else {
                return -1;
            }
        }
        return 1;
    }

    public static void closeSever() {
        connection().close();
    }

    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("type", "0");
        param.put("model", "AI");
        JenkinsUtil.getJenkinsJob(connection());
        for (Map.Entry<String, Job> entry : JenkinsUtil.getJenkinsJob(connection()).entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        JenkinsUtil.buildJob(connection(),"test", param);
    }
}