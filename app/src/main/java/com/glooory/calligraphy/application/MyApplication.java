package com.glooory.calligraphy.application;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().hideThreadInfo().methodCount(1);
    }

}
