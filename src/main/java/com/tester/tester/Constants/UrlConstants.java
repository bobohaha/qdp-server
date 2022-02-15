package com.tester.tester.Constants;

public class UrlConstants {
    private static final String APP_DOMAIN = "https://m.ipalfish.com/klian";
    private static final String TEST_APP_DOMAIN = "https://test.ipalfish.com/klian";
    private static final String MESSAGE_DOMAIN = "https://sea.pri.ibanyu.com";
    private static final String TEST_MESSAGE_DOMAIN = "https://sea-test.pri.ibanyu.com";
    private static final String OP_DOMAIN = "https://www.ibanyu.com:30000";
    private static final String TEST_OP_DOMAIN = "https://test.ipalfish.com:30000";
    private static final String CRM_DOMAIN = "https://www.ibanyu.com:30006";
    private static final String TEST_CRM_DOMAIN = "https://test.ipalfish.com:30006";

    public static final String TEST_LOGIN_URL = TEST_APP_DOMAIN + "/account/login";
    public static final String ONLINE_LOGIN_URL = APP_DOMAIN + "/account/login";
    public static final String TEST_APPOINTMENT_URL = TEST_APP_DOMAIN + "/ugc/interactclass/classroomtime/hold";
    public static final String ONLINE_APPOINTMENT_URL = APP_DOMAIN + "/ugc/interactclass/classroomtime/hold";
    public static final String TEST_OP_LOGIN_URL = TEST_OP_DOMAIN + "/auth/loginwithphone";
    public static final String ONLINE_OP_LOGIN_URL = OP_DOMAIN + "/auth/loginwithphone";
    public static final String TEST_CRM_LOGIN_URL = TEST_CRM_DOMAIN + "/auth/loginwithphone";
    public static final String ONLINE_CRM_LOGIN_URL = CRM_DOMAIN + "/auth/loginwithphone";


    public static final String TEST_DEVICE_INFO_URL = TEST_OP_DOMAIN + "/opapi/stat/assign";
    public static final String TEST_LESSON_INFO_URL = TEST_OP_DOMAIN + "/opapi/ugc/curriculum/classroom/sign/tea/table/dist";
}
