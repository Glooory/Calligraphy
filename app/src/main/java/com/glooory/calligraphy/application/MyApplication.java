package com.glooory.calligraphy.application;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Glooory on 2016/5/15 0015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
        LeakCanary.install(this);
    }

}
