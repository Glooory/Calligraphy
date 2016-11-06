package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.HdprintedAlpFragment;
import com.glooory.calligraphy.fragments.HdprintedDesFragment;
import com.glooory.calligraphy.fragments.HdprintedTipsFragment;
import com.glooory.calligraphy.fragments.ItalicAlpFragment;
import com.glooory.calligraphy.fragments.ItalicDesFragment;
import com.glooory.calligraphy.fragments.ItalicTipsFragment;
import com.glooory.calligraphy.fragments.RdhandAlpFragment;
import com.glooory.calligraphy.fragments.RdhandDesFragment;
import com.glooory.calligraphy.fragments.RdhandTipsFragment;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Glooo on 2016/6/15 0015.
 */
public class FontActivity extends BaseActivity implements MaterialTabListener {
    public static final int TAB_DES = 0;
    public static final int TAB_ALP = 1;
    public static final int TAB_TIPS = 2;
    private Toolbar mToolbar;
    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private int activityType = 0;
    Fragment fragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        activityType = getIntent().getIntExtra(Constants.ACTIVITY_INDEX, 0);
        setupToolbar();
        setupTabs();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.font_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (activityType) {
            case Constants.ACTIVITY_ITALIC_INDEX:
                this.setTitle(R.string.font_italic);
                break;
            case Constants.ACTIVITY_ROUNDHAND_INDEX:
                this.setTitle(R.string.font_round_hand);
                break;
            case Constants.ACTIVITY_HANDPRINTED_INDEX:
                this.setTitle(R.string.font_hand_printed);
                break;
        }
    }

    private void setupTabs() {
        mTabHost = (MaterialTabHost) findViewById(R.id.italy_material_tab_host);
        mPager = (ViewPager) findViewById(R.id.italy_view_pager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mTabHost.addTab(mTabHost.newTab().setText(getResources().getStringArray(R.array.tab_titles)[i]).setTabListener(this));
        }
    }



    @Override
    public void onTabSelected(MaterialTab tab) {
        mPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        FragmentManager fragmentManager;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case TAB_DES:
                    if (activityType == Constants.ACTIVITY_ITALIC_INDEX) {
                        fragment = new ItalicDesFragment();
                    } else if (activityType == Constants.ACTIVITY_ROUNDHAND_INDEX) {
                        fragment = new RdhandDesFragment();
                    } else if (activityType == Constants.ACTIVITY_HANDPRINTED_INDEX) {
                        fragment = new HdprintedDesFragment();
                    }
                    break;
                case TAB_ALP:
                    if (activityType == Constants.ACTIVITY_ITALIC_INDEX) {
                        fragment = new ItalicAlpFragment();
                    } else if (activityType == Constants.ACTIVITY_ROUNDHAND_INDEX) {
                        fragment = new RdhandAlpFragment();
                    } else if (activityType == Constants.ACTIVITY_HANDPRINTED_INDEX) {
                        fragment = new HdprintedAlpFragment();
                    }
                    break;
                case TAB_TIPS:
                    if (activityType == Constants.ACTIVITY_ITALIC_INDEX) {
                        fragment = new ItalicTipsFragment();
                    } else if (activityType == Constants.ACTIVITY_ROUNDHAND_INDEX) {
                        fragment = new RdhandTipsFragment();
                    } else if (activityType == Constants.ACTIVITY_HANDPRINTED_INDEX) {
                        fragment = new HdprintedTipsFragment();
                    }
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

}
