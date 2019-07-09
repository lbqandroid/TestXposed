package com.xposed.testxposed.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.xposed.testxposed.BuildConfig;

import de.robv.android.xposed.XSharedPreferences;

public class SharedPref {
    private static final String PREFS_FILE = "XPOSEDSHAREPREF";

    private SharedPreferences mySharedPref;
    private static XSharedPreferences myXsharedPref;

    public SharedPref(Context appContext) {
        mySharedPref = appContext.getSharedPreferences(PREFS_FILE, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
    }

    public void setSharedPref(String key, String value) {
        try {
            mySharedPref.edit().putString(key, value).apply();
        } catch (Exception e) {
            System.out.println("setSharedPref ERROR: " + e.getMessage());
        }
    }

    public void setIntSharedPref(String key, int value) {
        try {
            mySharedPref.edit().putInt(key, value).apply();
        } catch (Exception e) {
            System.out.println("setintSharedPref ERROR: " + e.getMessage());
        }
    }

    public void setFloatSharedPref(String key, float value) {
        try {
            mySharedPref.edit().putFloat(key, value).apply();
        } catch (Exception e) {
            System.out.println("setintSharedPref ERROR: " + e.getMessage());
        }
    }

    public void setBooleanSharedPref(String key, boolean value) {
        try {
            mySharedPref.edit().putBoolean(key, value).apply();
        } catch (Exception e) {
            System.out.println("setintSharedPref ERROR: " + e.getMessage());
        }
    }

    public static XSharedPreferences getMyXSharedPref() {
        if (myXsharedPref != null) {
//            myXsharedPref.reload();
            return myXsharedPref;
        }
        myXsharedPref = new XSharedPreferences(BuildConfig.APPLICATION_ID, PREFS_FILE);
        return myXsharedPref;
    }

    public static String getXValue(String key) {
        String value = "";
        try {
            value = getMyXSharedPref().getString(key, "");
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }

    public static int getIntXValue(String key) {
        int value = 0;
        try {
            value = getMyXSharedPref().getInt(key, 1);
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }

    public static float getFloatXValue(String key) {
        float value = 0;
        try {
            value = getMyXSharedPref().getFloat(key, 1);
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }

    public static boolean getBooleanXValue(String key) {
        boolean value = false;
        try {
            value = getMyXSharedPref().getBoolean(key, false);
        } catch (Exception e) {
            System.out.println("getSharedPref ERROR: " + e.getMessage());
        }
        return value;
    }
}
