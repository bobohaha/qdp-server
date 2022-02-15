package com.tester.tester.Util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static String JsonFormatString(JSONObject json) {
        return json.toString();
    }

    public static JSONObject stringFormatJson (String string) {
        return JSONObject.fromObject(string);
    }
}
