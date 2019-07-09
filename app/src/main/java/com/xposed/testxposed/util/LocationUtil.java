package com.xposed.testxposed.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.xposed.testxposed.bean.LocationBean;
import com.xposed.testxposed.config.SpKey;

import java.util.List;

public class LocationUtil {

    public static String lacationToString(Context context) {
        String gps = getGPSLocation(context);
        String cellId = getCellId(context);

        StringBuffer str = new StringBuffer();
        str.append("gps = " + gps + "\n");
        str.append("cellId = " + cellId + "\n");

        return str.toString();
    }

    public static void hook(Context context) {
        int num = SPUtils.getInstance().getInt("location_hook_num", 1);
        num += 1;
        SPUtils.getInstance().put("location_hook_num", num);

        SharedPref sharedPref = new SharedPref(context);
//        sharedPref.setFloatSharedPref("latitude", (float) (38.964057 + num));
//        sharedPref.setFloatSharedPref("longitude", (float) (117.261360 + num));
//        sharedPref.setIntSharedPref("gms_lac", num);
//        sharedPref.setIntSharedPref("gms_cid", num);
//        sharedPref.setIntSharedPref("cdma_BaseStationLatitude", num);
//        sharedPref.setIntSharedPref("cdma_BaseStationLongitude", num);
//        sharedPref.setIntSharedPref("cdma_BaseStationId", num);
//        sharedPref.setIntSharedPref("cdma_SystemId", num);
//        sharedPref.setIntSharedPref("cdma_NetworkId", num);

        LocationBean bean = new LocationBean();
        bean.setBaseStationType(2);
        bean.setLatitude(38.1111 + num);
        bean.setLongitude(117.1111 + num);
        bean.setGsmLac(num);
        bean.setGmsCid(num);
        bean.setCdmaBaseStationLatitude(num);
        bean.setCdmaBaseStationLongitude(num);
        bean.setCdmaBaseStationId(num);
        bean.setCdmaSystemId(num);
        bean.setCdmaNetworkId(num);
        sharedPref.setSharedPref(SpKey.JSON_HOOK_LOCATION, GsonUtils.toJson(bean));
    }

    private static final String TAG = "DevLocationReader";

    /**
     * 获取终端GPS位置
     *
     * @param context 应用上下文
     * @return 终端位置(LONG, LATI)
     */
    @SuppressLint("MissingPermission")
    private static String getGPSLocation(Context context) {

        String gpsInfo = null;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (null != locationManager) {

            //获取当前可用的位置控制器
            List<String> list = locationManager.getProviders(true);
            if (null != list) {
                for (String provider : list) {
                    LogUtils.w(TAG, "current provider = " + provider);
                    if (locationManager.isProviderEnabled(provider)) {
                        LogUtils.w(TAG, "current provider = " + provider + " is enable");
                        Location location = locationManager.getLastKnownLocation(provider);
                        if (null != location) {
                            gpsInfo = String.valueOf(location.getLongitude()) + "," + String.valueOf(location.getLatitude());
                            LogUtils.w(TAG, "current provider return gps = " + gpsInfo);
                            break;
                        }
                    }
                }
            }
        } else {
            LogUtils.w(TAG, "location manager is null!");
        }

        return gpsInfo;
    }

    /**
     * 获取小区基站信息
     *
     * @param context 应用上下文
     * @return GSM返回0, CID, LAC，CDMA返回1,BID,NID,SID
     */
    @SuppressLint("MissingPermission")
    private static String getCellId(Context context) {

        String pos = null;

        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            CellLocation location = manager.getCellLocation();
            if (null != location) {
                if (location instanceof GsmCellLocation) {
                    GsmCellLocation gsm = ((GsmCellLocation) manager.getCellLocation());
                    if (null != gsm) {
                        int lac = gsm.getLac();
                        int cid = gsm.getCid();
                        pos = "0," + cid + "," + lac;
                    }

                } else if (location instanceof CdmaCellLocation) {
                    CdmaCellLocation cdma = (CdmaCellLocation) manager.getCellLocation();
                    if (null != cdma) {
                        int nid = cdma.getNetworkId();
                        int bid = cdma.getBaseStationId();
                        int sid = cdma.getSystemId();
                        int lat = cdma.getBaseStationLatitude();
                        int lng = cdma.getBaseStationLongitude();
                        pos = "1," + bid + "," + nid + "," + sid + ",lat= " + lat + ",lng= " + lng;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pos;
    }

}
