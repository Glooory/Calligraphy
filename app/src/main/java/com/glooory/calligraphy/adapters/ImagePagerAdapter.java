package com.glooory.calligraphy.adapters;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.Constants.Urls;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.views.PinchImageView;
import com.glooory.calligraphy.views.PinchImageViewPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private int mIndex;
    private String[] MY_URLS = new String[] {};
    private int[] MY_CHAR_IDS = new int[]{};
    private boolean isFromUrl;
    private LayoutInflater inflater;
    private DisplayImageOptions options;
    private PinchImageViewPager pager;
    private ImagePagerActivity mContext;

    public ImagePagerAdapter(ImagePagerActivity context, int index, PinchImageViewPager pager) {
        this.mContext = context;
        this.pager = pager;
        this.mIndex = index;
        inflater = LayoutInflater.from(context);

        init();
        if (isFromUrl) {
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_empty)
                    .showImageOnFail(R.mipmap.ic_error)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .build();
        }
    }

    private void init() {
        switch (mIndex) {
            case Constants.WORKS_IMAGE_INDEX:
                MY_URLS = Urls.WORKURLS;
                isFromUrl = true;
                break;
            case Constants.FLOURISHING_IMAGE_INDEX:
                MY_URLS = Urls.FLOURISHINGURLS;
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
                return Urls.WORKURLS.length;
            // TODO: 2016/5/16 0016
            case Constants.FLOURISHING_IMAGE_INDEX:
                return Urls.FLOURISHINGURLS.length;
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
        View imagelayout = inflater.inflate(R.layout.item_viewpager, container, false);
        assert imagelayout != null;
        PinchImageView imageView = (PinchImageView) imagelayout.findViewById(R.id.viewpager_item_image);

        if (isFromUrl) {
            final ProgressBar progressBar = (ProgressBar) imagelayout.findViewById(R.id.viewpager_item_progressbar);

            ImageLoader.getInstance().displayImage(MY_URLS[position], imageView, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        } else {
            imageView.setImageResource(MY_CHAR_IDS[position]);
        }

        container.addView(imagelayout, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finishThis();
            }
        });

        return imagelayout;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        FrameLayout frameLayout = (FrameLayout) object;
        PinchImageView pinchImageView = (PinchImageView) frameLayout.findViewById(R.id.viewpager_item_image);
        if (isFromUrl) {

            final ProgressBar progressBar = (ProgressBar) frameLayout.findViewById(R.id.viewpager_item_progressbar);

            ImageLoader.getInstance().displayImage(MY_URLS[position], pinchImageView, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        } else {
            pinchImageView.setImageResource(MY_CHAR_IDS[position]);
        }
        pager.setMainPinchImageView(pinchImageView);
    }
}
