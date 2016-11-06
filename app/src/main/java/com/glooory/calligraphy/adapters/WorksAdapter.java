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

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.utils.ImageLoadUtil;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.modul.CalliWork;

import java.util.List;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class WorksAdapter extends RecyclerView.Adapter<WorksAdapter.WorkHolder> {
    private LayoutInflater mInflater;
    private int DETAIL_IMG_INDEX;
    private Context mContext;
    private int resizeWidth = 0;
    private List<CalliWork> mWorks;

    public WorksAdapter(Context context, int worksIndex) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        if (worksIndex == Constants.WORKS_NORMAL_INDEX) {
            DETAIL_IMG_INDEX = Constants.WORKS_IMAGE_INDEX;
        } else {
            DETAIL_IMG_INDEX = Constants.FLOURISHING_IMAGE_INDEX;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        resizeWidth = (int) ((displayMetrics.widthPixels - 24 * displayMetrics.density) * 0.5);
    }

    @Override
    public WorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_works, parent, false);
        return new WorkHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkHolder holder, final int position) {
        if (mWorks != null && mWorks.size() > 0) {
            CalliWork calliWork = mWorks.get(position);
            float mRatio = ((float) calliWork.getWidth()) / ((float) calliWork.getHeight());
            int resizeHeight = (int) (resizeWidth / mRatio);
            ViewGroup.LayoutParams params = holder.workImg.getLayoutParams();
            params.width = resizeWidth;
            params.height = resizeHeight;
            holder.workImg.setLayoutParams(params);
            ImageLoadUtil.loadImageWithPlaceHolders(mContext, holder.workImg,
                    Constants.IMG_URL_PREFIX + mWorks.get(position).getKey(),
                    resizeWidth, resizeHeight);

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
        } else {
            //图片网址信息还没解析完，先加载占位图
            ImageLoadUtil.loadImage(mContext, holder.workImg, R.drawable.place_holder_d, resizeWidth, resizeWidth);
        }
    }

    @Override
    public int getItemCount() {
        if (mWorks != null && mWorks.size() > 0) {
            return mWorks.size();
        }
        return 10;
    }

    static class WorkHolder extends RecyclerView.ViewHolder {
        private ImageView workImg;

        public WorkHolder(View itemView) {
            super(itemView);
            workImg = (ImageView) itemView.findViewById(R.id.card_works_img);
        }
    }

    public void setWorkList(List<CalliWork> list) {
        if (mWorks != null) {
            this.mWorks.clear();
        }
        this.mWorks = list;
        notifyDataSetChanged();
    }

}
