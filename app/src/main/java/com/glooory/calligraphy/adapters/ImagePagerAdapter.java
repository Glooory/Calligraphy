package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.FontActivity;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.net.ImageLoader;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by Glooory on 2016/5/16 0016.
 */
public class ImagePagerAdapter extends PagerAdapter {
    private int mFontType;
    private int[] mCharResIds;
    private LayoutInflater inflater;
    private Context mContext;

    public ImagePagerAdapter(Context context, int fontType) {
        this.mContext = context;
        this.mFontType = fontType;
        inflater = LayoutInflater.from(context);
        initData();
    }

    private void initData() {
        switch (mFontType) {
            case FontActivity.FONT_ITALIC:
                mCharResIds = Constants.ITALIC_CHAR_IDS;
                break;
            case FontActivity.FONT_ROUND_HAND:
                mCharResIds = Constants.ROUNDHAND_CHAR_IDS;
                break;
            case FontActivity.FONT_HAND_PRINTED:
                mCharResIds = Constants.HANDPRINTED_CHAR_IDS;
                break;
        }
    }

    @Override
    public int getCount() {
        return mCharResIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = (ImageView) inflater.inflate(R.layout.item_viewpager, container, false);
        ImageLoader.load(mContext, imageView, mCharResIds[position]);
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
        container.addView(imageView);
        return imageView;
    }
}
