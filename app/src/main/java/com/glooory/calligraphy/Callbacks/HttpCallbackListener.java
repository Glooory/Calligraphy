package com.glooory.calligraphy.callbacks;

/**
 * Created by Glooo on 2016/7/17 0017.
 */
public interface HttpCallbackListener {

    public abstract void onHttpRequestFinish();

    public abstract void onHttpRequestError(Exception e);

}
