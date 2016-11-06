package com.glooory.calligraphy.utils;

import android.text.TextUtils;

/**
 * Created by Glooory on 2016/11/5 0005 17:11.
 */

public class Utils {

    /**
     * 检查图片是否为Gif图片
     * @param type
     * @return
     */
    public static boolean checkIsGif(String type) {
        if (type == null || TextUtils.isEmpty(type)) {
            return false;
        }
        if (type.contains("gif") || type.contains("GIF") || type.contains("Gif")) {
            return true;
        }
        return false;
    }

}
