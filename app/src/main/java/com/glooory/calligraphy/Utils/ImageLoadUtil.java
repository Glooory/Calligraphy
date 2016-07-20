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
    public static void loadImage(Context context, ImageView imageView, int resourceId) {
        Picasso.with(context).load(resourceId).centerInside().into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, int resourceId
            , int width, int height) {
        Picasso.with(context).load(resourceId).centerInside().resize(width, height).into(imageView);
    }

    /**
     * 从网络中加载
     */
    public static void loadImage(Context context,ImageView imageView, String url) {
        Picasso.with(context).load(url).placeholder(R.drawable.load_ori_img_progress).into(imageView);
    }

    public static void loadImageWithPlaceHolders(Context context, ImageView imageView, String url, int width, int height) {
        Picasso picasso = Picasso.with(context);
//        picasso.setIndicatorsEnabled(true);
        picasso.load(url)
                .resize(width, height)
                .centerInside()
                .placeholder(R.drawable.progress_animation)
                .error(R.mipmap.ic_error)
                .into(imageView);
    }
}
