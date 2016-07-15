package com.glooory.calligraphy.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.AnimationUtil;
import com.glooory.calligraphy.activities.ImagePagerActivity;

/**
 * Created by Glooo on 2016/6/15 0015.
 */
public class CharAdapter extends RecyclerView.Adapter<CharAdapter.CharHolder> {
    private LayoutInflater mInflater;
    private int[] charImgIds = new int[]{};
    private int mPrePosition = 0;
    private Context mContext;
    private int displayActivityIndex = 0;
    private LruCache<String, Drawable> mMemoryCache;
    private Resources mResources;

    public CharAdapter(Context context, int fontType) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initCharImgIds(fontType);
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<>(cacheSize);
        mResources = context.getResources();
    }

    /**
     * 根据传入的参数确定使用那种字母表
     */
    private void initCharImgIds(int fontType) {
        switch (fontType) {
            case Constants.FONT_ITALIC:
                charImgIds = Constants.ITALIC_CHAR_IDS;
                displayActivityIndex = Constants.ITALIC_IMAGE_INDEX;
                break;
            case Constants.FONT_ROUNDHAND:
                charImgIds = Constants.ROUNDHAND_CHAR_IDS;
                displayActivityIndex = Constants.ROUNDHAND_IMAGE_INDEX;
                break;
            case Constants.FONT_HANDPRINTED:
                charImgIds = Constants.HANDPRINTED_CHAR_IDS;
                displayActivityIndex = Constants.HANDPRINTED_IMAGE_INDEX;
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
    public void onBindViewHolder(CharHolder holder, final int position) {

        Drawable charImg = mMemoryCache.get(String.valueOf(charImgIds[position]));
        if (charImg != null) {
            holder.charImage.setImageDrawable(charImg);
        } else {
            holder.charImage.setImageResource(charImgIds[position]);
        }

        mMemoryCache.put(String.valueOf(charImgIds[position]), mResources.getDrawable(charImgIds[position]));

        if (position > mPrePosition) {
            AnimationUtil.animateSunblind(holder, true);
        } else {
            AnimationUtil.animateSunblind(holder, false);
        }

        mPrePosition = position;

        holder.charImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra(Constants.IMAGE_PAGER_INDEX, displayActivityIndex);
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
        return charImgIds.length;
    }

    static class CharHolder extends RecyclerView.ViewHolder {
        private ImageView charImage;

        public CharHolder(View itemView) {
            super(itemView);
            charImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }

    public void clearMemoryCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
        }
    }
}
