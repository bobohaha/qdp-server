package com.tester.tester.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MapUtil {
    private static final Map<String, String> map = null;
    private static Logger logger = LoggerFactory.getLogger(MapUtil.class);
    public static Map<String, String> getMapObject(String[] param) {

        if (param != null && param.length > 0) {
            if (param.length % 2 == 0) {
                for (int i = 0; i < param.length; i = i + 2) {
                    map.put(param[i], param[i+1]);
                }
            } else {
                logger.error("header param need [key1, value1, key2, value2...]");
            }
        }
        return map;
    }
}
