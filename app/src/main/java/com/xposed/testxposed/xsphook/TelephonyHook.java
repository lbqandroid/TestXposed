package com.xposed.testxposed.xsphook;

import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.xposed.testxposed.bean.TelephonyBean;
import com.xposed.testxposed.config.SpKey;
import com.xposed.testxposed.util.SharedPref;

import java.util.HashMap;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TelephonyHook {

    public static void hook(XC_LoadPackage.LoadPackageParam mLpp) {

        String json = SharedPref.getXValue(SpKey.JSON_HOOK_TELEPHONY);
        if (TextUtils.isEmpty(json)) {
            return;
        }

        final TelephonyBean bean = GsonUtils.fromJson(json, TelephonyBean.class);
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getDeviceId", bean.getImei());
//        XHookMethod("com.android.internal.telephony.PhoneSubInfo",mLpp.classLoader, "getDeviceId", SharedPref.getXValue("imei"));
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSubscriberId", bean.getImsi());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getLine1Number", bean.getLine1Number());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSimSerialNumber", bean.getSimSerialNumber());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSimCountryIso", bean.getSimCountryIso());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSimOperator", bean.getSimOperator());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSimOperatorName", bean.getSimOperatorName());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getNetworkCountryIso", bean.getNetworkCountryIso());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getNetworkOperator", bean.getNetworkOperator());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getNetworkOperatorName", bean.getNetworkOperatorName());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getDeviceSoftwareVersion", bean.getDeviceSoftwareVersion());

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getPhoneCount", bean.getPhoneCount());
        }

        //设置手机信息 无论手机是否插入了sim卡 都会模拟出SIM卡的信息 APP获得SIM卡消息时返回该手机已有SIM卡
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getPhoneType", bean.getPhoneType());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getNetworkType", bean.getNetworkType());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "getSimState", bean.getSimState());
        XHookMethod(android.telephony.TelephonyManager.class.getName(), mLpp.classLoader, "hasIccCard", bean.isHasIccCard());

        //修改ANDROID_ID
        if (!TextUtils.isEmpty(bean.getAndroidId())) {
            XposedBridge.hookAllMethods(android.provider.Settings.System.class, "getString", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Settings.Secure.ANDROID_ID, bean.getAndroidId());
                    if (param.args.length > 1 && param.args[1] != null && hashMap.containsKey(param.args[1].toString())) {
                        param.setResult(hashMap.get(param.args[1].toString()));
                    }
                }
            });

            XposedBridge.hookAllMethods(android.provider.Settings.Secure.class, "getString", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Settings.Secure.ANDROID_ID, bean.getAndroidId());
                    if (param.args.length > 1 && param.args[1] != null && hashMap.containsKey(param.args[1].toString())) {
                        param.setResult(hashMap.get(param.args[1].toString()));
                    }
                }
            });
        }
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
