package com.glooory.calligraphy.application;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso =  new Picasso.Builder(this).
                downloader(new OkHttpDownloader(getCacheDir(), 150 * 1024 * 1024)).build();
        Picasso.setSingletonInstance(picasso);
        Logger.init();
        LeakCanary.install(this);
    }

}
