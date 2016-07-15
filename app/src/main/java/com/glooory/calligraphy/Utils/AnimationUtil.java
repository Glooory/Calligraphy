package com.glooory.calligraphy.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Glooo on 2016/5/9 0009.
 */
public class AnimationUtil {

    public static void animateSunblind(RecyclerView.ViewHolder holder, boolean goesDown) {
        int holderHeight = holder.itemView.getHeight();
        holder.itemView.setPivotY(goesDown == true ? 0 : holderHeight);
        holder.itemView.setPivotX(holderHeight);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator animatorRotaion = ObjectAnimator.ofFloat(holder.itemView, "rotationX", goesDown == true ? -90f : 90F, 0.0f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.6f, 1.0f);
        animatorSet.playTogether(animatorTranslateY, animatorRotaion, animatorScaleX);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

}
