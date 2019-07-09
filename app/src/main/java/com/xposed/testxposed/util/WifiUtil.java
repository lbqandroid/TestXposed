package com.xposed.testxposed.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.xposed.testxposed.bean.WifiBean;
import com.xposed.testxposed.config.SpKey;

public class WifiUtil {

    public static String wifiToString() {

        String wifimac = DeviceUtils.getMacAddress();

        String macAddress = "";
        String bssid = "";
        String ssid = "";
        int ipAddress = 0;
        WifiManager wifi = (WifiManager) Utils.getApp()
                .getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();
            if (info != null) {
                macAddress = info.getMacAddress();
                bssid = info.getBSSID();
                ssid = info.getSSID();
                ipAddress = info.getIpAddress();
            }
        }

        StringBuffer str = new StringBuffer();
        str.append("macAddress = " + macAddress + "\n");
        str.append("bssid = " + bssid + "\n");
        str.append("ssid = " + ssid + "\n");
        str.append("ipAddress = " + ipAddress + "\n");

        return str.toString();
    }

    public static void hookData(Context context) {
        String hook = "-hook-";
        int num = SPUtils.getInstance().getInt("wifi_hook_num", 1);
        num += 1;
        hook = hook + num;
        SPUtils.getInstance().put("wifi_hook_num", num);

        SharedPref sharedPref = new SharedPref(context);
//        sharedPref.setSharedPref("macAddress", "macAddress" + hook);
//        sharedPref.setSharedPref("bssid", "bssid" + hook);
//        sharedPref.setSharedPref("ssid", "ssid" + hook);

        WifiBean bean = new WifiBean();
        bean.setMacAddress("macAddress" + hook);
        bean.setBssid("bssid" + hook);
        bean.setSsid("ssid" + hook);
        sharedPref.setSharedPref(SpKey.JSON_HOOK_WIFI, GsonUtils.toJson(bean));
    }
}
