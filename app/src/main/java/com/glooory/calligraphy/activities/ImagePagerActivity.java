package com.glooory.calligraphy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.adapters.ImagePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/5/16 0016.
 */
public class ImagePagerActivity extends BaseActivity {
    public static final String KEY_POSTION = "key_position";

    @BindView(R.id.toolbar_image_pager)
    Toolbar mToolbar;
    @BindView(R.id.view_pager_image_pager)
    ViewPager mViewPager;

    public static void launch(Activity activity, int fontType, int position) {
        Intent intent = new Intent(activity, ImagePagerActivity.class);
        intent.putExtra(FontActivity.FONT_TYPE_INDEX, fontType);
        intent.putExtra(KEY_POSTION, position);
        if (Build.VERSION.SDK_INT >= 21) {
            activity.startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        ButterKnife.bind(this);
        int fontType = getIntent().getIntExtra(FontActivity.FONT_TYPE_INDEX, FontActivity.FONT_ITALIC);
        int position = getIntent().getIntExtra(KEY_POSTION, 0);
        initView(fontType, position);
    }

    private void initView(int fontType, int position) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ImagePagerAdapter adapter = new ImagePagerAdapter(this, fontType);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);
    }
}
