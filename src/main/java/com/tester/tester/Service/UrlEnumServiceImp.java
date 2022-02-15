package com.tester.tester.Service;
import com.tester.tester.Constants.UrlConstants;
import com.tester.tester.Util.CommonUtil;

public enum UrlEnumServiceImp implements UrlEnumService {

    OP_LOGIN_URL {
        @Override
        public String getUrl(int env) {
            if (CommonUtil.isOnline(env)) {
                return UrlConstants.ONLINE_OP_LOGIN_URL;
            }else {
                return UrlConstants.TEST_OP_LOGIN_URL;
            }
        }
    },

    APP_LOGIN_URL {
        @Override
        public String getUrl(int env) {
            if (CommonUtil.isOnline(env)) {
                return UrlConstants.ONLINE_LOGIN_URL;
            }else {
                return UrlConstants.TEST_LOGIN_URL;
            }
        }
    },
    CRM_LOGIN_URL {
        @Override
        public String getUrl(int env) {
            if (CommonUtil.isOnline(env)) {
                return UrlConstants.ONLINE_CRM_LOGIN_URL;
            }else {
                return UrlConstants.TEST_CRM_LOGIN_URL;
            }
        }
    },

    APPOINTMENT_URL {
        @Override
        public String getUrl(int env) {
            if (CommonUtil.isOnline(env)) {
                return UrlConstants.ONLINE_APPOINTMENT_URL;
            }else {
                return UrlConstants.TEST_APPOINTMENT_URL;
            }
        }
    }
}
