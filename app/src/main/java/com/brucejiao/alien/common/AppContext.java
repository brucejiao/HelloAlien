package com.brucejiao.alien.common;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.LinkedList;

public class AppContext extends Application {

    private static AppContext app;

    private LinkedList<Activity> activitys = null;


    public AppContext() {
        app = this;
    }

    public static synchronized AppContext getInstance() {
        if (app == null) {
            app = new AppContext();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        activitys = new LinkedList<Activity>();
        //内存泄漏检测
        LeakCanary.install(this);
        registerUncaughtExceptionHandler();
        //TODO 获取网络状态并给出相应的界面


    }

    // 注册App异常崩溃处理器
    private void registerUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
    }
}