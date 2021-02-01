package com.marstalk.testng;

public class NgUtils {
    private NgUtils() {

    }

    public static boolean notNull() {
        return true;
    }

    public static boolean notNull(String name) {
        return null != name && !"".equals(name.trim());
    }
}
