package com.glooory.calligraphy.Callbacks;

import com.glooory.calligraphy.modul.CalliWork;

import java.util.List;

/**
 * Created by Glooo on 2016/7/18 0018.
 * 从缓存文件中读取数据的回调
 */
public interface FileCacheListener {

    public abstract void readCacheFinish(List<CalliWork> workList);

    public abstract void readCacheError(Exception e);

}
