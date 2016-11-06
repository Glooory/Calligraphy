package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.utils.ImageLoadUtil;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.fragments.WorksFragment;
import com.glooory.calligraphy.views.PinchImageView;
import com.glooory.calligraphy.views.PinchImageViewPager;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private int mIndex;
    private int[] MY_CHAR_IDS = new int[]{};
    private boolean isFromUrl;
    private LayoutInflater inflater;
    private PinchImageViewPager pager;
    private Context mContext;

    public ImagePagerAdapter(Context context, int index, PinchImageViewPager pager) {
        this.mContext = context;
        this.pager = pager;
        this.mIndex = index;
        inflater = LayoutInflater.from(context);

        init();
    }

    private void init() {
        switch (mIndex) {
            case Constants.WORKS_IMAGE_INDEX:
                isFromUrl = true;
                break;
            case Constants.FLOURISHING_IMAGE_INDEX:
                isFromUrl = true;
                break;
            case Constants.ITALIC_IMAGE_INDEX:
                MY_CHAR_IDS = Constants.ITALIC_CHAR_IDS;
                isFromUrl = false;
                break;
            case Constants.ROUNDHAND_IMAGE_INDEX:
                MY_CHAR_IDS = Constants.ROUNDHAND_CHAR_IDS;
                isFromUrl = false;
                break;
            case Constants.HANDPRINTED_IMAGE_INDEX:
                MY_CHAR_IDS = Constants.HANDPRINTED_CHAR_IDS;
                isFromUrl = false;
                break;
        }
    }

    @Override
    public int getCount() {
        switch (mIndex) {
            case Constants.WORKS_IMAGE_INDEX:
                return WorksFragment.mWorks.size();
            case Constants.FLOURISHING_IMAGE_INDEX:
                return WorksFragment.mWorks.size();
            case Constants.ITALIC_IMAGE_INDEX:
                return Constants.ITALIC_CHAR_IDS.length;
            case Constants.ROUNDHAND_IMAGE_INDEX:
                return Constants.ROUNDHAND_CHAR_IDS.length;
            case Constants.HANDPRINTED_IMAGE_INDEX:
                return Constants.HANDPRINTED_CHAR_IDS.length;
        }
        return 0;
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
        PinchImageView imageView = (PinchImageView) inflater.inflate(R.layout.item_viewpager, container, false);

        if (isFromUrl) {
            ImageLoadUtil.loadImage(mContext, imageView,
                    Constants.IMG_URL_PREFIX + WorksFragment.mWorks.get(position).getKey());
        } else {
            imageView.setImageResource(MY_CHAR_IDS[position]);
        }

        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImagePagerActivity) mContext).finishThis();
            }
        });

        return imageView;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        PinchImageView imageView = (PinchImageView) object;
        if (isFromUrl) {
            ImageLoadUtil.loadImage(mContext, imageView,
                    Constants.IMG_URL_PREFIX + WorksFragment.mWorks.get(position).getKey());
        } else {
            imageView.setImageResource(MY_CHAR_IDS[position]);
        }
        pager.setMainPinchImageView(imageView);
    }

}
