package com.glooory.calligraphy.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

/**
 * Created by Glooo on 2016/7/17 0017.
 */
public class WelcomeActivity extends AppCompatActivity {
    private ImageView mWelcomeImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mWelcomeImg = (ImageView) findViewById(R.id.welcome_img);
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.welcome_anim);
        animator.setTarget(mWelcomeImg);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
