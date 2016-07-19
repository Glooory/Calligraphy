package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.adapters.ImagePagerAdapter;
import com.glooory.calligraphy.views.PinchImageViewPager;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class ImagePagerActivity extends BaseActivity {
    private PinchImageViewPager pager;
    private ImagePagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pinch_viewpager);

        int index = getIntent().getIntExtra(Constants.IMAGE_PAGER_INDEX, 33);
        int position = getIntent().getIntExtra(Constants.IMAGE_POSITION, 0);

        pager = (PinchImageViewPager) findViewById(R.id.pinch_view_pager);
        mAdapter = new ImagePagerAdapter(this, index, pager);
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(position);
    }

    public void finishThis() {
        finishSelf();
    }
}
