package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.glooory.calligraphy.Constants.Urls;
import com.glooory.calligraphy.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class GridViewAdapter extends BaseAdapter {

    public static final int WORKS_GRIDVIEW = 0;
    public static final int FLOURISHING_GRIDVIEW = 1;
    private LayoutInflater inflater;
    private DisplayImageOptions options;
    private static final String[] WORK_URLS = Urls.WORKURLS;
    private static final String[] FLOURISHING_URLS = Urls.FLOURISHINGURLS;
    private int mGridViewIndex;
    private String[] MY_URLS = new String[]{};

    public GridViewAdapter(Context context, int gridViewIndex) {
        mGridViewIndex = gridViewIndex;
        inflater = LayoutInflater.from(context);

        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_empty)
                .showImageOnFail(R.mipmap.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        if (gridViewIndex == WORKS_GRIDVIEW) {
            MY_URLS = WORK_URLS;
        } else {
            MY_URLS = FLOURISHING_URLS;
        }
    }

    @Override
    public int getCount() {
        switch (mGridViewIndex) {
            case WORKS_GRIDVIEW:
                return WORK_URLS.length;
            case FLOURISHING_GRIDVIEW:
                return FLOURISHING_URLS.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (convertView == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_grid_image, parent, false);
            assert view != null;
            holder.imageView = (ImageView) view.findViewById(R.id.gridview_item_image);
            holder.progressBar = (ProgressBar) view.findViewById(R.id.gridview_item_progressbar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(MY_URLS[position], holder.imageView, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setProgress(0);
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
            }

        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                holder.progressBar.setProgress(Math.round(100.0f * current / total));
            }
        });
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
}
