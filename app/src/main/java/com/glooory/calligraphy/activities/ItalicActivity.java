package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.ItalicDesFragment;
import com.glooory.calligraphy.fragments.ItalyAlpFragment;
import com.glooory.calligraphy.fragments.ItalyTipsFragment;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicActivity extends AppCompatActivity implements MaterialTabListener {

    public static final int TAB_DES = 0;
    public static final int TAB_ALP = 1;
    public static final int TAB_TIPS = 2;
    private Toolbar mToolbar;
    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_italic);
        setupToolbar();
        setupTabs();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.italy_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            Fragment fragment = null;
            switch (position) {
                case TAB_DES:
                    fragment = new ItalicDesFragment();
                    break;
                case TAB_ALP:
                    fragment = new ItalyAlpFragment();
                    break;
                case TAB_TIPS:
                    fragment = new ItalyTipsFragment();
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
