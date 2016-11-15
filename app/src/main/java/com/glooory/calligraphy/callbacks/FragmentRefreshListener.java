package com.glooory.calligraphy.callbacks;

/**
 * Created by Glooory on 2016/9/17 0017 10:04.
 * Fragment需要刷新数据时，请求Activity对应操作的回调
 */
public interface FragmentRefreshListener {

    void requestRefresh();

    void requestRefreshDone();

}
