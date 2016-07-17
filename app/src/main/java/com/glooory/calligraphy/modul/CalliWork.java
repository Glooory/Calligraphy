package com.glooory.calligraphy.modul;

/**
 * Created by Glooo on 2016/7/17 0017.
 */
public class CalliWork {

    /**用于展示的作品
     * id : 108823680
     * farm : farm1
     * bucket : hbimg
     * key : 9dc1d37042087a658ec5435620adb899eabb739c6e24-vP6C1q
     * type : image/jpeg
     * height : 363
     * frames : 1
     * width : 480
     * themeColor : 868686
     * colors : [{"color":8816262,"ratio":0.43},{"color":9868950,"ratio":0.14},{"color":7829367,"ratio":0.11}]
     */

    private int id;
    private String key;
    private String type;
    private int height;
    private int width;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
