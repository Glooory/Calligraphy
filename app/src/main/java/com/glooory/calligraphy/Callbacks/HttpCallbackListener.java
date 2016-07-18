package com.glooory.calligraphy.Callbacks;

/**
 * Created by Glooo on 2016/7/17 0017.
 */
public interface HttpCallbackListener {

    public abstract void onFinish(String res);

    public abstract void onError();

}
