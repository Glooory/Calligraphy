package com.glooory.calligraphy.net;

import com.glooory.calligraphy.entity.FileLoadBean;
import com.glooory.calligraphy.utils.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * Created by Glooory on 2016/9/13 0013 12:57.
 * 自定义ResponseBody类。 实现RxBus发送事件
 */
public class FileResponseBody extends ResponseBody {
    Response originalResponse;

    public FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    @Override
    public long contentLength() {
        return originalResponse.body().contentLength(); // 文件的总长度
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesReaded = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;

                //通过RxBus发布进度信息
                RxBus.getDefault().post(new FileLoadBean(bytesReaded, contentLength()));

                return bytesRead;
            }
        });
    }
}
