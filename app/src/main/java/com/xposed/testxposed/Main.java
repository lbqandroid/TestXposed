package com.xposed.testxposed;

import com.xposed.testxposed.hook.Hook;
import com.xposed.testxposed.xsphook.LocationHook;
import com.xposed.testxposed.xsphook.OsBuildHook;
import com.xposed.testxposed.xsphook.TelephonyHook;
import com.xposed.testxposed.xsphook.WifiHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {

    private static final String TAG = "MainTag";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam mLpp) throws Throwable {

        new Hook().HookTest(mLpp); // 动态生效 不用重启

        if (!mLpp.packageName.equals("com.xposed.testxposed")
                && !mLpp.packageName.equals("com.xposed.showmobile"))
            return;

        XposedBridge.log("Loaded app: " + mLpp.packageName);
        try {
            OsBuildHook.hook(mLpp);
            TelephonyHook.hook(mLpp);
            WifiHook.hook(mLpp);
            LocationHook.hook(mLpp);
//            OtherHook.hook(mLpp);
        } catch (Exception e) {
            XposedBridge.log(e.getMessage());
        }

    }

    private static String GetCatValue(String s) {
        return "xposed----" + s;
    }
}
