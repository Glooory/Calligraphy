package com.glooory.calligraphy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.AlphabetFragment;
import com.glooory.calligraphy.fragments.HdprintedDesFragment;
import com.glooory.calligraphy.fragments.HdprintedTipsFragment;
import com.glooory.calligraphy.fragments.ItalicDesFragment;
import com.glooory.calligraphy.fragments.ItalicTipsFragment;
import com.glooory.calligraphy.fragments.RdhandDesFragment;
import com.glooory.calligraphy.fragments.RdhandTipsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/11/11 0011 10:31.
 */

public class FontActivity extends BaseActivity {
    public static final String FONT_TYPE_INDEX = "font_type";
    public static final int FONT_ITALIC = 0;
    public static final int FONT_ROUND_HAND = 1;
    public static final int FONT_HAND_PRINTED = 2;

    @BindView(R.id.toolbar_font)
    Toolbar mToolbar;
    @BindView(R.id.table_layout_font)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_font)
    ViewPager mViewPager;

    private int mFontType;
    private String[] mFontTitles = new String[4];
    private FontPagerAdapter mAdapter;

    public static void launch(Activity activity, int fontType) {
        Intent intent = new Intent(activity, FontActivity.class);
        intent.putExtra(FONT_TYPE_INDEX, fontType);
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
        setContentView(R.layout.activity_font);
        ButterKnife.bind(this);
        mFontType = getIntent().getIntExtra(FONT_TYPE_INDEX, FONT_ITALIC);
        mFontTitles = getResources().getStringArray(R.array.fonts);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mFontTitles[mFontType]);

        mAdapter = new FontPagerAdapter(getSupportFragmentManager(), mFontType);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class FontPagerAdapter extends FragmentPagerAdapter{
        private int fontTypeIndex;
        private String[] titles = new String[3];

        public FontPagerAdapter(FragmentManager fm, int fontType) {
            super(fm);
            this.fontTypeIndex = fontType;
            titles = getApplicationContext().getResources().getStringArray(R.array.font_content_titles);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    if (fontTypeIndex == FONT_ITALIC) {
                        fragment = ItalicDesFragment.newInstance();
                    } else if (fontTypeIndex == FONT_ROUND_HAND) {
                        fragment = RdhandDesFragment.newInstance();
                    } else if (fontTypeIndex == FONT_HAND_PRINTED) {
                        fragment = HdprintedDesFragment.newInstance();
                    }
                    break;
                case 1:
                    if (fontTypeIndex == FONT_ITALIC) {
                        fragment = AlphabetFragment.newInstance(FONT_ITALIC);
                    } else if (fontTypeIndex == FONT_ROUND_HAND) {
                        fragment = AlphabetFragment.newInstance(FONT_ROUND_HAND);
                    } else if (fontTypeIndex == FONT_HAND_PRINTED) {
                        fragment = AlphabetFragment.newInstance(FONT_HAND_PRINTED);
                    }
                    break;
                case 2:
                    if (fontTypeIndex == FONT_ITALIC) {
                        fragment = ItalicTipsFragment.newInstance();
                    } else if (fontTypeIndex == FONT_ROUND_HAND) {
                        fragment = RdhandTipsFragment.newInstance();
                    } else if (fontTypeIndex == FONT_HAND_PRINTED) {
                        fragment = HdprintedTipsFragment.newInstance();
                    }
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
