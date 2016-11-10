package com.glooory.calligraphy.api;

import com.glooory.calligraphy.entity.PinInfoBean;
import com.glooory.calligraphy.entity.PinsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Glooory on 2016/11/5 0005 15:13.
 */

public interface PinsApi {
    //获取某个画板的采集图片
    //http://api.huaban.com/boards/30752981/pins?limit=?
    @GET("boards/{boardId}/pins")
    Observable<PinsListBean> getPinsList(@Path("boardId") String boardId,
                                         @Query("limit") int limit);

    //https://api.huaban.com/boards/1234566/pins?max=123456&limit=20
    @GET("boards/{boardId}/pins")
    Observable<PinsListBean> getPinsListMax(@Path("boardId") String boardId,
                                            @Query("max") String max,
                                            @Query("limit") int limit);

    //请求单张图片
    //http://api.huaban.com/pins/786135762
    @GET("pins/{pinId}")
    Observable<PinInfoBean> getPinInfo(@Path("pinId") String pinId);

}
