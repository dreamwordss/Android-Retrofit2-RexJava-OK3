package com.lbl.networkframe;

import android.app.Application;
import android.content.Context;


/**
 * Created by bilang on 2017/2/9.
 */

public class MyApplication extends Application {

    private static MyApplication application;
    public static final String CONFIG_INF = "redlips_configG"; // SharedPreferences文件名

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        Logger.init("").hideThreadInfo().methodCount(0)
//                .logLevel(LogLevel.FULL);
    }

    public static Context getContext() {
        return application;
    }

}
