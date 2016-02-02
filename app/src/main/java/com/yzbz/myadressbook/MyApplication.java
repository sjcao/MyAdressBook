package com.yzbz.myadressbook;

import android.app.Application;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * Created by senjucao on 2016/1/28.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        SMSSDK.initSDK(this, "f38c65dfb3b0", "4ca9caa4c69b74421321a5020163c975");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}