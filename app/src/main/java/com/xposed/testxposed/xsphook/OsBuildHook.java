package com.xposed.testxposed.xsphook;

import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.xposed.testxposed.BuildConfig;
import com.xposed.testxposed.bean.OsBuildBean;
import com.xposed.testxposed.config.SpKey;
import com.xposed.testxposed.util.SharedPref;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class OsBuildHook {

    public static void hook(XC_LoadPackage.LoadPackageParam mLpp) {

        //修改手机系统信息 此处是手机的基本信息 包括厂商 信号 ROM版本 安卓版本 主板 设备名 指纹名称等信息
        String json = SharedPref.getXValue(SpKey.JSON_HOOK_BUILD);
        if (TextUtils.isEmpty(json)) {
            return;
        }
        final OsBuildBean bean = GsonUtils.fromJson(json, OsBuildBean.class);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", bean.getModel());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER", bean.getManufacturer());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND", bean.getBrand());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "HARDWARE", bean.getHardware());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", bean.getBoard());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "SERIAL", bean.getSerial());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            XposedHelpers.findAndHookMethod(android.os.Build.class.getName(), mLpp.classLoader, "getSerial", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(bean.getSerial());
                }
            });
        }
        XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", bean.getDevice());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "ID", bean.getId());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", bean.getProduct());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "DISPLAY", bean.getDisplay());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "FINGERPRINT", bean.getFingerprint());
        XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", bean.getCpu1());
        XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "RELEASE", bean.getVersionRelease());
        XposedHelpers.findAndHookMethod(android.os.Build.class.getName(), mLpp.classLoader, "getRadioVersion", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(bean.getRadioVersion());
            }
        });
    }

}
