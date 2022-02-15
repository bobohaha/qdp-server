package com.tester.tester.Util;

import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class CookieUtil {
    public static String getCookie(Response response) {

        List<String> cookiesList = response.headers().values("Set-Cookie");
        List<String> cookies = new ArrayList<>();
        for (String cookie : cookiesList) {
            cookies.add(cookie.split(";")[0]);
        }
        return StringUtil.listToString(cookies, ";");
    }
}
