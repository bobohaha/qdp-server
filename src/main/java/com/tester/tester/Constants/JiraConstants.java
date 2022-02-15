package com.tester.tester.Constants;

import java.util.ArrayList;

public class JiraConstants {
    public static final String JIRA_URL = "http://jira.pri.ibanyu.com/";
    public static final String JIRA_USERNAME = "zoufangbo6690";
    public static final String JIRA_PASSWORD = ".Aini.920806..";

    private static final String BUSINESS_JUNIOR_KEY= "PALFISHKID";
    private static final String BUSINESS_TEACHING_KEY = "TEACHING";
    private static final String BUSINESS_READING_KEY = "READING";
    private static final String BUSINESS_CRM_KEY = "CRM";
    private static final String BUSINESS_HUIBEN_KEY = "HUIBEN";
    private static final String BUSINESS_INTERNET_SALES_KEY = "WANGXIAO";
    public static final ArrayList BUSINESS = new ArrayList<String>() {{
        add(BUSINESS_JUNIOR_KEY);
        add(BUSINESS_TEACHING_KEY);
        add(BUSINESS_READING_KEY);
        add(BUSINESS_CRM_KEY);
        add(BUSINESS_HUIBEN_KEY);
        add(BUSINESS_INTERNET_SALES_KEY);
    }};
}
