package com.xposed.testxposed.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.xposed.testxposed.bean.TelephonyBean;
import com.xposed.testxposed.config.SpKey;

public class TelephonyUtil {

    @SuppressLint("MissingPermission")
    public static String telephonyToString(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        String imsi = tm.getSubscriberId();
        String number = tm.getLine1Number();
        String simserialnumber = tm.getSimSerialNumber();
        String simcountryiso = tm.getSimCountryIso();
        String simoperator = tm.getSimOperator();
        String simoperatorname = tm.getSimOperatorName();
        String networkcountryiso = tm.getNetworkCountryIso();
        String networkoperator = tm.getNetworkOperator();
        String networkoperatorname = tm.getNetworkOperatorName();
        int phoneCount = 1;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            phoneCount = tm.getPhoneCount();
        }
        String softwareVersion = tm.getDeviceSoftwareVersion();
        int phoneType = tm.getPhoneType();
        int networkType = tm.getNetworkType();
        int simState = tm.getSimState();
        boolean hasIccCard = tm.hasIccCard();

        StringBuffer str = new StringBuffer();
        str.append("imei = " + imei + "\n");
        str.append("imsi = " + imsi + "\n");
        str.append("number = " + number + "\n");
        str.append("simserialnumber = " + simserialnumber + "\n");
        str.append("simcountryiso = " + simcountryiso + "\n");
        str.append("simoperator = " + simoperator + "\n");
        str.append("simoperatorname = " + simoperatorname + "\n");
        str.append("networkcountryiso = " + networkcountryiso + "\n");
        str.append("networkoperator = " + networkoperator + "\n");
        str.append("networkoperatorname = " + networkoperatorname + "\n");
        str.append("phoneCount = " + phoneCount + "\n");
        str.append("softwareVersion = " + softwareVersion + "\n");
        str.append("phoneType = " + phoneType + "\n");
        str.append("networkType = " + networkType + "\n");
        str.append("simState = " + simState + "\n");
        str.append("hasIccCard = " + hasIccCard + "\n");

        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        str.append("ANDROID_ID = " + ANDROID_ID + "\n");

        return str.toString();
    }

    @SuppressLint("MissingPermission")
    public static void hookData(Context context) {
        String hook = "-hook-";
        int num = SPUtils.getInstance().getInt("telephony_hook_num", 1);
        num += 1;
        hook = hook + num;
        SPUtils.getInstance().put("telephony_hook_num", num);

        SharedPref sharedPref = new SharedPref(context);
//        sharedPref.setSharedPref("imei", "imei" + hook);
//        sharedPref.setSharedPref("imsi", "imsi" + hook);
//        sharedPref.setSharedPref("number", "number" + hook);
//        sharedPref.setSharedPref("simserialnumber", "simserialnumber" + hook);
//        sharedPref.setSharedPref("simcountryiso", "simcountryiso" + hook);
//        sharedPref.setSharedPref("simoperator", "simoperator" + hook);
//        sharedPref.setSharedPref("simoperatorname", "simoperatorname" + hook);
//        sharedPref.setSharedPref("networkcountryiso", "networkcountryiso" + hook);
//        sharedPref.setSharedPref("networkoperator", "networkoperator" + hook);
//        sharedPref.setSharedPref("networkoperatorname", "networkoperatorname" + hook);
//        sharedPref.setIntSharedPref("phoneCount", 2);
//        sharedPref.setSharedPref("softwareVersion", "softwareVersion" + hook);
//        sharedPref.setIntSharedPref("phoneType", num);
//        sharedPref.setIntSharedPref("networkType", num);
//        sharedPref.setIntSharedPref("simState", num);
//        sharedPref.setBooleanSharedPref("hasIccCard", true);
//        sharedPref.setSharedPref("android_id", "ANDROID_ID" + hook);

        TelephonyBean bean = new TelephonyBean();
        bean.setImei("imei" + hook);
        bean.setImsi("imsi" + hook);
        bean.setLine1Number("line1number" + hook);
        bean.setSimSerialNumber("simserialnumber" + hook);
        bean.setSimCountryIso("simcountryiso" + hook);
        bean.setSimOperator("simoperator" + hook);
        bean.setSimOperatorName("simoperatorname" + hook);
        bean.setNetworkCountryIso("networkcountryiso" + hook);
        bean.setNetworkOperator("networkoperator" + hook);
        bean.setNetworkOperatorName("networkoperatorname" + hook);
        bean.setDeviceSoftwareVersion("softwareVersion" + hook);
        bean.setPhoneCount(2);
        bean.setPhoneType(num);
        bean.setNetworkType(num);
        bean.setSimState(num);
        bean.setHasIccCard(true);
        bean.setAndroidId("ANDROID_ID" + hook);
        sharedPref.setSharedPref(SpKey.JSON_HOOK_TELEPHONY, GsonUtils.toJson(bean));
    }
}
