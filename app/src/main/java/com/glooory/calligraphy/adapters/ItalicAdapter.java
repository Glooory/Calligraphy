package com.glooory.calligraphy.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.anim.AnimationUtils;

/**
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicAdapter extends RecyclerView.Adapter<ItalicAdapter.ItalyItemHolder> {

    private int[] charImageIds;
    private LayoutInflater inflater;
    private int mPreviousPosition = 0;
    private Activity mContext;

    public ItalicAdapter(Activity context) {
        this.mContext = context;
        initCharImageIds();
        inflater = LayoutInflater.from(context);
    }

    private void initCharImageIds() {
        charImageIds = Constants.ITALIC_CHAR_IDS;
    }

    @Override
    public ItalyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_char, parent, false);
        ItalyItemHolder itemHolder = new ItalyItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItalyItemHolder holder, final int position) {
        holder.itemImage.setImageResource(charImageIds[position]);

        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
        } else {
            AnimationUtils.animateSunblind(holder, false);
        }

        mPreviousPosition = position;

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra(Constants.IMAGE_PAGER_INDEX, Constants.ITALIC_IMAGE_INDEX);
                intent.putExtra(Constants.IMAGE_POSITION, position);
                if (Build.VERSION.SDK_INT >= 21) {
                    mContext.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mContext).toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return charImageIds.length;
    }

    static class ItalyItemHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        public ItalyItemHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }


}
