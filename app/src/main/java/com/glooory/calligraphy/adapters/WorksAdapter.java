package com.glooory.calligraphy.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.Constants.Urls;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.ImageLoadUtil;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.modul.CalliWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class WorksAdapter extends RecyclerView.Adapter<WorksAdapter.WorkHolder> {
    private LayoutInflater mInflater;
    private static String[] MY_URLS = new String[]{};
    private int DETAIL_IMG_INDEX;
    private Context mContext;
    private int resizeSize = 0;
    private List<CalliWork> mWorks = new ArrayList<>();

    public WorksAdapter(Context context, int worksIndex) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        if (worksIndex == Constants.WORKS_NORMAL_INDEX) {
            MY_URLS = Urls.WORKURLS;
            DETAIL_IMG_INDEX = Constants.WORKS_IMAGE_INDEX;
        } else {
            MY_URLS = Urls.FLOURISHINGURLS;
            DETAIL_IMG_INDEX = Constants.FLOURISHING_IMAGE_INDEX;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        resizeSize = (int) (displayMetrics.widthPixels * 0.75);
    }

    @Override
    public WorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_works, parent, false);
        return new WorkHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkHolder holder, final int position) {
//        ViewGroup.LayoutParams params = holder.workImg.getLayoutParams();
//        params.width = resizeSize;
//        params.height = (int) (resizeSize * mRatio[position]);
//        holder.workImg.setLayoutParams(params);
//        ImageLoadUtil.loadImage(mContext, holder.workImg, MY_URLS[position]);
        ImageLoadUtil.loadImageWithPlaceHolders(mContext, holder.workImg,
                Constants.IMG_URL_PREFIX + mWorks.get(position).getKey(),
                resizeSize, resizeSize);

        holder.workImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra(Constants.IMAGE_PAGER_INDEX, DETAIL_IMG_INDEX);
                intent.putExtra(Constants.IMAGE_POSITION, position);
                if (Build.VERSION.SDK_INT >= 21) {
                    mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext).toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorks.size();
    }

    static class WorkHolder extends RecyclerView.ViewHolder {
        private ImageView workImg;

        public WorkHolder(View itemView) {
            super(itemView);
            workImg = (ImageView) itemView.findViewById(R.id.card_works_img);
        }
    }

    public void setWorkList(List<CalliWork> list) {
        this.mWorks = list;
        notifyDataSetChanged();
    }

}