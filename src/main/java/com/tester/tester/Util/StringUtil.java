package com.tester.tester.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringUtil {
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static String listToString(List<String> list, String tag) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + tag);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    public static int getInt(String str, Integer defaultData) {
        int inum = defaultData;

        if (isEmpty(str)) {
            return inum;
        }

        try {
            inum = Integer.valueOf(str.trim()).intValue();
        } catch (NumberFormatException e) {
            logger.warn("把String转换成int数据========== " + str);
        }
        return inum;
    }

    public static Boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(String str, String searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return "";
        }
        return str.substring(start);
    }

    public static long getLong(String str, Long defaultData) {
        Long lnum = defaultData;

        if (isEmpty(str)) {
            return lnum;
        }
        try {
            lnum = Long.valueOf(str.trim()).longValue();
        } catch (NumberFormatException e) {
            logger.warn("把String 转换为 long======== " + str);
        }
        return lnum;
    }

    public static Boolean getBoolean(String str, Boolean defaultData) {
        Boolean lnum = defaultData;

        if (isEmpty(str)) {
            return lnum;
        }
        try {
            lnum = Boolean.valueOf(str.trim()).booleanValue();
        } catch (NumberFormatException e) {
            logger.warn("把String 转换为 long======== " + str);
        }
        return lnum;
    }
}