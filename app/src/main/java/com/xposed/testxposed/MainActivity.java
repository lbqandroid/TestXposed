package com.xposed.testxposed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.xposed.testxposed.util.LocationUtil;
import com.xposed.testxposed.util.OsBuildUtil;
import com.xposed.testxposed.util.OtherUtil;
import com.xposed.testxposed.util.TelephonyUtil;
import com.xposed.testxposed.util.WifiUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @SuppressLint("MissingPermission")
    private void initView() {
        final TextView tvText = findViewById(R.id.tv_text);
        Button btnHook = findViewById(R.id.btn_hook);

        // 先初始化一遍数据，避免数据为空导致的问题

        tvText.setText(OsBuildUtil.OsBuildToString()
                + TelephonyUtil.telephonyToString(MainActivity.this)
                + WifiUtil.wifiToString()
                + LocationUtil.lacationToString(MainActivity.this)
                + OtherUtil.otherToString(MainActivity.this)
        );

        btnHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OsBuildUtil.hookData(MainActivity.this);
                TelephonyUtil.hookData(MainActivity.this);
                WifiUtil.hookData(MainActivity.this);
                LocationUtil.hook(MainActivity.this);
                tvText.setText(OsBuildUtil.OsBuildToString()
                        + TelephonyUtil.telephonyToString(MainActivity.this)
                        + WifiUtil.wifiToString()
                        + LocationUtil.lacationToString(MainActivity.this)
                );

//                clearData();
            }
        });
    }

    private void clearData(){
        String clearData = "pm clear com.xposed.showmobile";
        ShellUtils.CommandResult result = ShellUtils.execCmd(clearData, true, true);
        LogUtils.d("result:" + result.toString());
    }
}
