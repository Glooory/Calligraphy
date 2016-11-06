package com.glooory.calligraphy.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Glooory on 2016/11/5 0005 14:47.
 */

public class ImageLoader {

    private ImageLoader() {
    }

    public static void load(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .crossFade()
                .into(imageView);
    }

}
