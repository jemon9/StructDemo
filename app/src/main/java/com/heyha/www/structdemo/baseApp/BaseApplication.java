package com.heyha.www.structdemo.baseApp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Heyha on 2017/1/19.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext(){
        return instance;
    }

    public static Resources getAppResources(){
        return instance.getResources();
    }
}
