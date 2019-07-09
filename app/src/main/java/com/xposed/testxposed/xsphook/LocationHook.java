package com.xposed.testxposed.xsphook;

import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.xposed.testxposed.bean.LocationBean;
import com.xposed.testxposed.config.SpKey;
import com.xposed.testxposed.util.SharedPref;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationHook {

    public static void hook(XC_LoadPackage.LoadPackageParam mLpp) {

        String json = SharedPref.getXValue(SpKey.JSON_HOOK_LOCATION);
        if (TextUtils.isEmpty(json)) {
            return;
        }
        final LocationBean bean = GsonUtils.fromJson(json, LocationBean.class);

        //修改GSM制式手机的基站信息
        XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", mLpp.classLoader,
                "getCellLocation", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        if (bean.getBaseStationType() == 1) {
                            // 只使用gps定位
                            param.setResult(null);
                        } else if (bean.getBaseStationType() == 2) {
                            GsmCellLocation gsmCellLocation = new GsmCellLocation();
                            param.setResult(gsmCellLocation);
                        } else {
                            CdmaCellLocation cdmaCellLocation = new CdmaCellLocation();
                            param.setResult(cdmaCellLocation);
                        }
                    }
                });

        XposedHelpers.findAndHookMethod("android.net.wifi.WifiManager", mLpp.classLoader, "getScanResults", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                if (bean.getBaseStationType() == 1) {
                    // 只使用gps定位
                    param.setResult(null);
                }
            }
        });

        //修改位置信息
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastKnownLocation", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Location l = new Location(LocationManager.GPS_PROVIDER);
                l.setLatitude(bean.getLatitude());
                l.setLongitude(bean.getLongitude());
                l.setAccuracy(100f);
                l.setTime(System.currentTimeMillis());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    l.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                }
                param.setResult(l);
            }
        });

        XHookMethod(android.telephony.gsm.GsmCellLocation.class.getName(), mLpp.classLoader, "getLac", bean.getGsmLac());
        XHookMethod(android.telephony.gsm.GsmCellLocation.class.getName(), mLpp.classLoader, "getCid", bean.getGmsCid());

        //修改CDMA制式手机的基站信息
        XHookMethod(android.telephony.cdma.CdmaCellLocation.class.getName(),
                mLpp.classLoader, "getBaseStationLatitude", bean.getCdmaBaseStationLatitude());
        XHookMethod(android.telephony.cdma.CdmaCellLocation.class.getName(),
                mLpp.classLoader, "getBaseStationLongitude", bean.getCdmaBaseStationLongitude());
        XHookMethod(android.telephony.cdma.CdmaCellLocation.class.getName(),
                mLpp.classLoader, "getBaseStationId", bean.getCdmaBaseStationId());
        XHookMethod(android.telephony.cdma.CdmaCellLocation.class.getName(),
                mLpp.classLoader, "getSystemId", bean.getCdmaSystemId());
        XHookMethod(android.telephony.cdma.CdmaCellLocation.class.getName(),
                mLpp.classLoader, "getNetworkId", bean.getCdmaNetworkId());
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
