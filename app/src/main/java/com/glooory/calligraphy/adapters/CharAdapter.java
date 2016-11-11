package com.glooory.calligraphy.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.FontActivity;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.net.ImageLoader;
import com.glooory.calligraphy.utils.AnimationUtil;

/**
 * Created by Glooory on 2016/6/15 0015.
 */
public class CharAdapter extends RecyclerView.Adapter<CharAdapter.CharHolder> {
    private LayoutInflater mInflater;
    private int[] charImgIds = new int[]{};
    private int mPrePosition = 0;
    private Context mContext;
    private int mItemImgWidth;
    private float mImgRatio;
    private int mFontType;

    public CharAdapter(Context context, int fontType) {
        this.mContext = context;
        this.mFontType = fontType;
        mInflater = LayoutInflater.from(context);
        initCharImgIds(fontType);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mItemImgWidth = (int) (displayMetrics.widthPixels - 16 * displayMetrics.density);//图片显示的宽度
    }

    /**
     * 根据传入的参数确定使用那种字母表
     */
    private void initCharImgIds(int fontType) {
        switch (fontType) {
            case FontActivity.FONT_ITALIC:
                charImgIds = Constants.ITALIC_CHAR_IDS;
                mImgRatio = Constants.RATIO_ITALIC;
                break;
            case FontActivity.FONT_ROUND_HAND:
                charImgIds = Constants.ROUNDHAND_CHAR_IDS;
                mImgRatio = Constants.RATIO_ROUND_HAND;
                break;
            case FontActivity.FONT_HAND_PRINTED:
                charImgIds = Constants.HANDPRINTED_CHAR_IDS;
                mImgRatio = Constants.RATIO_HAND_PRINTED;
                break;
        }
    }

    @Override
    public CharHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.card_char, parent, false);
        CharHolder charHolder = new CharHolder(itemView);
        return charHolder;
    }

    @Override
    public void onBindViewHolder(final CharHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.charImage.getLayoutParams();
        params.width = mItemImgWidth;
        params.height = (int) (mItemImgWidth * mImgRatio);
        holder.charImage.setLayoutParams(params);
        ImageLoader.load(mContext, holder.charImage, charImgIds[position]);
        if (position > mPrePosition) {
            AnimationUtil.animateSunblind(holder, true);
        } else {
            AnimationUtil.animateSunblind(holder, false);
        }
        mPrePosition = position;
        holder.charImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePagerActivity.launch((Activity) mContext, mFontType, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return charImgIds.length;
    }

    static class CharHolder extends RecyclerView.ViewHolder {
        private ImageView charImage;

        public CharHolder(View itemView) {
            super(itemView);
            charImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }
}
