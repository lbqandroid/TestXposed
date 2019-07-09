package com.xposed.testxposed.util;

import android.content.Context;

import java.util.Locale;

public class OtherUtil {

    public static String otherToString(Context context) {
        return "language = " + getCurrentSystemLanguage(context) + "\n"
                + "resolution = " + getResolution(context) + "\n";
    }

    public static void hook() {

    }

    private static String getCurrentSystemLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    private static String getResolution(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels + "*" +
                context.getResources().getDisplayMetrics().heightPixels
                + "-dpi = " + context.getResources().getDisplayMetrics().densityDpi
                + "-density=" + context.getResources().getDisplayMetrics().density;
    }
}
