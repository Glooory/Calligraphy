package com.glooory.calligraphy.entity;

/**
 * Created by Glooory on 2016/9/13 0013 13:04.
 * 封装了下载进度和文件大小的实体类
 */
public class FileLoadBean {

    /**
     * 文件的大小
     */
    private long total;

    /**
     * 已下载的大小
     */
    private long progress;

    public FileLoadBean(long progress, long total) {
        this.progress = progress;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
