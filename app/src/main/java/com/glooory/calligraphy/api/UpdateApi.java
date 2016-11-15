package com.glooory.calligraphy.api;


import com.glooory.calligraphy.entity.VersionInfoBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Glooory on 2016/10/31 0031 19:40.
 */

public interface UpdateApi {
    //检查是否有新版本
    @GET("UpdateInfo.json")
    Observable<VersionInfoBean> checkUpdateInfo();

    @GET("{path}")
    Call<ResponseBody> downloadFile(@Path("path") String path);
}
