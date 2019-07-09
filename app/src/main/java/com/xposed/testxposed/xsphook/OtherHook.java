package com.xposed.testxposed.xsphook;

import android.util.DisplayMetrics;
import android.view.Display;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XCallback;

public class OtherHook {
    /*
      屏幕相关
     */
    public static void hook(XC_LoadPackage.LoadPackageParam loadPkgParam) {

        XposedHelpers.findAndHookMethod("android.view.Display", loadPkgParam.classLoader, "getMetrics", DisplayMetrics.class, new XC_MethodHook(XCallback.PRIORITY_LOWEST) {

            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                int dpi = 480;
                DisplayMetrics metrics = (DisplayMetrics) param.args[0];
                metrics.densityDpi = dpi;

            }
        });

        XposedHelpers.findAndHookMethod("android.view.Display", loadPkgParam.classLoader, "getRealMetrics", DisplayMetrics.class, new XC_MethodHook(XCallback.PRIORITY_LOWEST) {

            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                int dpi = 480;
                DisplayMetrics metrics = (DisplayMetrics) param.args[0];
                metrics.densityDpi = dpi;

            }

        });

        XposedHelpers.findAndHookMethod("android.view.Display", loadPkgParam.classLoader, "getMetrics", DisplayMetrics.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                float sdensity = 3;
                DisplayMetrics metrics = (DisplayMetrics) param.args[0];
                metrics.density = sdensity;

            }

        });

        // 宽
        XposedHelpers.findAndHookMethod(Display.class, "getMetrics", DisplayMetrics.class, new XC_MethodHook(XCallback.PRIORITY_LOWEST) {
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                final int zhenwidth = 1080;
                DisplayMetrics metrics = (DisplayMetrics) param.args[0];
                metrics.widthPixels = zhenwidth;

            }
        });

        // 高
        XposedHelpers.findAndHookMethod(Display.class, "getMetrics", DisplayMetrics.class, new XC_MethodHook(XCallback.PRIORITY_LOWEST) {
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                final int zhenheight = 1920;
                DisplayMetrics metrics = (DisplayMetrics) param.args[0];
                metrics.heightPixels = zhenheight;

            }
        });

    }

    private static int tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 320;
        }
    }

    private static float tryParsefloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return (float) 480.0;
        }
    }

}


