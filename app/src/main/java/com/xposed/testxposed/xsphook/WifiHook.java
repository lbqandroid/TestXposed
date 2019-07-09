package com.xposed.testxposed.xsphook;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.xposed.testxposed.bean.WifiBean;
import com.xposed.testxposed.config.SpKey;
import com.xposed.testxposed.util.SharedPref;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiHook {

    public static void hook(XC_LoadPackage.LoadPackageParam mLpp) {

        //WIFI信息
        String json = SharedPref.getXValue(SpKey.JSON_HOOK_WIFI);
        if (TextUtils.isEmpty(json)) {
            return;
        }
        WifiBean bean = GsonUtils.fromJson(json, WifiBean.class);
        XHookMethod(android.net.wifi.WifiInfo.class.getName(), mLpp.classLoader, "getMacAddress", bean.getMacAddress());
        XHookMethod(android.net.wifi.WifiInfo.class.getName(), mLpp.classLoader, "getBSSID", bean.getBssid());
        XHookMethod(android.net.wifi.WifiInfo.class.getName(), mLpp.classLoader, "getSSID", bean.getSsid());
//        XHookMethod(android.net.wifi.WifiInfo.class.getName(), mLpp.classLoader, "getIpAddress", bean.getIpAddress());
//        XposedHelpers.findAndHookMethod(java.net.NetworkInterface.class.getName(),mLpp.classLoader, "getHardwareAddress", new Object[] {
//                new XC_MethodHook()
//                {
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable
//                    {
//                        //每个安卓系统中 至少存在5个以上的MAC地址
//                        //但大多数软件只修改了MAC和BSSID
//                        //真正的MAC修改是在此处理函数中监听每次访问.
//                    }
//                }});
    }

    private static String GetCatValue(String s) {
        return "xposed----" + s;
    }

    private static void XHookMethod(String packageName, ClassLoader classLoader, String methodName, final Object value) {
        XposedHelpers.findAndHookMethod(packageName, classLoader, methodName, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(value);
            }
        });
    }
}
