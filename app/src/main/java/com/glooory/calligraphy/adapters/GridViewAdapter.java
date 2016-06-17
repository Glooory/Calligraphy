package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.glooory.calligraphy.Constants.Urls;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.ImageLoadUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class GridViewAdapter extends BaseAdapter {

    public static final int WORKS_GRIDVIEW = 0;
    public static final int FLOURISHING_GRIDVIEW = 1;
    private LayoutInflater inflater;
    private static final String[] WORK_URLS = Urls.WORKURLS;
    private static final String[] FLOURISHING_URLS = Urls.FLOURISHINGURLS;
    private int mGridViewIndex;
    private String[] MY_URLS = new String[]{};
    private Context mContext;
    private int resizeSize = 0;

    public GridViewAdapter(Context context, int gridViewIndex) {
        mGridViewIndex = gridViewIndex;
        mContext = context;
        inflater = LayoutInflater.from(context);
        if (gridViewIndex == WORKS_GRIDVIEW) {
            MY_URLS = WORK_URLS;
        } else {
            MY_URLS = FLOURISHING_URLS;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        resizeSize = displayMetrics.widthPixels / 3;
        Logger.d(String.valueOf(resizeSize));
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
        ImageView imageView;
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_grid_image, parent, false);
        }
        imageView = (ImageView) view.findViewById(R.id.gridview_item_image);
        ImageLoadUtil.loadImageWithPlaceHolders(mContext, imageView, MY_URLS[position], resizeSize);
        return view;
    }
}
