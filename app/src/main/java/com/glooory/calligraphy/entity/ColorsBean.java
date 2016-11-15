package com.glooory.calligraphy.entity;

import com.google.gson.Gson;

/**
 * Created by Glooory on 2016/11/5 0005 15:05.
 */

public class ColorsBean {

    private int color;
    private double ratio;

    public static ColorsBean objectFromData(String str) {

        return new Gson().fromJson(str, ColorsBean.class);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

}
