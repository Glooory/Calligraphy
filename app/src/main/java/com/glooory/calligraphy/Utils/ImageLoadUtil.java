package com.glooory.calligraphy.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.glooory.calligraphy.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Glooo on 2016/6/15 0015.
 */
public class ImageLoadUtil {

    private ImageLoadUtil() {
    }

    /**
     * 从资源文件中加载
     */
    public static void loadImage(Context context,ImageView imageView, int resourceId) {
        Picasso.with(context).load(resourceId).into(imageView);
    }

    /**
     * 从网络中加载
     */
    public static void loadImage(Context context,ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }

    public static void loadImageWithPlaceHolders(Context context, ImageView imageView, String url, int resizeSize) {
        Picasso.with(context)
                .load(url)
                .resize(resizeSize, resizeSize)
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(imageView);
    }
}
