package com.xposed.testxposed.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.xposed.testxposed.bean.OsBuildBean;
import com.xposed.testxposed.config.SpKey;

public class OsBuildUtil {

    @SuppressLint("MissingPermission")
    public static String OsBuildToString() {
        String model = android.os.Build.MODEL;
        String manufacturer = android.os.Build.MANUFACTURER;
        String brand = android.os.Build.BRAND;
        String hardware = android.os.Build.HARDWARE;
        String board = android.os.Build.BOARD;
        String serial = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
        String device = android.os.Build.DEVICE;
        String id = android.os.Build.ID;
        String product = android.os.Build.PRODUCT;
        String display = android.os.Build.DISPLAY;
        String fingerprint = android.os.Build.FINGERPRINT;
        String cpu1 = android.os.Build.CPU_ABI;
        String versionRelease = Build.VERSION.RELEASE;
        String radioVersion = android.os.Build.getRadioVersion();

        StringBuffer str = new StringBuffer();
        //noinspection ConstantConditions
        str.append("model = " + model + "\n");
        str.append("manufacturer = " + manufacturer + "\n");
        str.append("brand = " + brand + "\n");
        str.append("hardware = " + hardware + "\n");
        str.append("board = " + board + "\n");
        str.append("serial = " + serial + "\n");
        str.append("device = " + device + "\n");
        str.append("id = " + id + "\n");
        str.append("product = " + product + "\n");
        str.append("display = " + display + "\n");
        str.append("fingerprint = " + fingerprint + "\n");
        str.append("cpu1 = " + cpu1 + "\n");
        str.append("versionRelease = " + versionRelease + "\n");
        str.append("radioVersion = " + radioVersion + "\n");
        return str.toString();
    }

    @SuppressLint("MissingPermission")
    public static void hookData(Context context) {
        String hook = "-hook-";
        int num = SPUtils.getInstance().getInt("build_hook_num", 1);
        num += 1;
        hook = hook + num;
        SPUtils.getInstance().put("build_hook_num", num);

        SharedPref sharedPref = new SharedPref(context);
//        sharedPref.setSharedPref("model", "model" + hook);
//        sharedPref.setSharedPref("manufacturer", "manufacturer" + hook);
//        sharedPref.setSharedPref("brand", "brand" + hook);
//        sharedPref.setSharedPref("hardware", "hardware" + hook);
//        sharedPref.setSharedPref("board", "board" + hook);
//        sharedPref.setSharedPref("serial", "serial" + hook);
//        sharedPref.setSharedPref("device", "device" + hook);
//        sharedPref.setSharedPref("id", "id" + hook);
//        sharedPref.setSharedPref("product", "product" + hook);
//        sharedPref.setSharedPref("display", "display" + hook);
//        sharedPref.setSharedPref("fingerprint", "fingerprint" + hook);
//        sharedPref.setSharedPref("cpu1", "cpu1" + hook);
//        sharedPref.setSharedPref("versionRelease", "versionRelease" + hook);
//        sharedPref.setSharedPref("radioVersion", "radioVersion" + hook);

        OsBuildBean bean = new OsBuildBean();
        bean.setModel("model" + hook);
        bean.setManufacturer("manufacturer" + hook);
        bean.setBrand("brand" + hook);
        bean.setHardware("hardware" + hook);
        bean.setBoard("board" + hook);
        bean.setSerial("serial" + hook);
        bean.setDevice("device" + hook);
        bean.setId("id" + hook);
        bean.setProduct("product" + hook);
        bean.setDisplay("display" + hook);
        bean.setFingerprint("fingerprint" + hook);
        bean.setCpu1("cpu1" + hook);
        bean.setVersionRelease("versionRelease" + hook);
        bean.setRadioVersion("radioVersion" + hook);

        sharedPref.setSharedPref(SpKey.JSON_HOOK_BUILD, GsonUtils.toJson(bean));
    }
}
