package com.glooory.calligraphy.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace / 2;
        outRect.right = mSpace / 2;
        outRect.bottom = mSpace;

        if (parent.getChildAdapterPosition(view) == 0 ||
                parent.getChildAdapterPosition(view) == 1) {
            outRect.top = mSpace;
        }
    }
}
