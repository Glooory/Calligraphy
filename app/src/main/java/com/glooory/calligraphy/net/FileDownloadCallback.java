package com.glooory.calligraphy.net;

import com.glooory.calligraphy.entity.FileLoadBean;
import com.glooory.calligraphy.utils.RxBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Glooory on 2016/9/13 0013 13:13.
 */
public abstract class FileDownloadCallback implements Callback<ResponseBody>{

    /**
     * 订阅下载进度
     */
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();

    /**
     * 目标文件夹
     */
    private String destFileDir;

    /**
     * 目标文件名
     */
    private String destFileName;

    public FileDownloadCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress(); //订阅下载进度
    }

    /**
     * 订阅文件的下载进度
     */
    private void subscribeLoadProgress() {
        rxSubscriptions.add(RxBus.getDefault().toObservable(FileLoadBean.class)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FileLoadBean>() {
                    @Override
                    public void call(FileLoadBean pinLoadBean) {
                        onLoading(pinLoadBean.getProgress(), pinLoadBean.getTotal());
                    }
                })
        );
    }

    /**
     * 成功后的回调
     * @param file
     */
    public abstract void onSuccess(File file);

    /**
     * 下载进度的回调
     * @param progress
     * @param total
     */
    public abstract void onLoading(long progress, long total);

    /**
     * 请求成功后保存文件
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        saveFile(response);
    }

    /**
     * 通过IO流写入文件
     * @param response
     */
    private File saveFile(retrofit2.Response<ResponseBody> response) {
        InputStream inputStream = null;
        FileOutputStream fos = null;
        byte[] buf = new byte[2048];
        int len;
        try {
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            inputStream = response.body().byteStream();
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }

            onSuccess(file);  //回调成功的接口
            unSubscribe();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 取消订阅
     */
    private void unSubscribe() {

        if (rxSubscriptions != null) {
            rxSubscriptions.unsubscribe();
        }

    }


}
