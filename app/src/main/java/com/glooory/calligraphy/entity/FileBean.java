package com.glooory.calligraphy.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Glooory on 2016/11/5 0005 15:04.
 */

public class FileBean {

    private int id;
    private String bucket;
    private String key;
    private String type;
    private int height;
    private int width;
    /**
     * color : 15198166
     * ratio : 0.39
     */

    private List<ColorsBean> colors;

    public static FileBean objectFromData(String str) {

        return new Gson().fromJson(str, FileBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<ColorsBean> getColors() {
        return colors;
    }

    public void setColors(List<ColorsBean> colors) {
        this.colors = colors;
    }
}
