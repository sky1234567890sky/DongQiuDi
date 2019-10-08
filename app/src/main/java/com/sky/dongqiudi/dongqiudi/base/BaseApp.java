package com.sky.dongqiudi.dongqiudi.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    public static BaseApp mBaseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApp = this;
    }

    public static BaseApp getInstance() {
        return mBaseApp;
    }

    public static Context getAppContext(){
        return mBaseApp.getApplicationContext();
    }
}
