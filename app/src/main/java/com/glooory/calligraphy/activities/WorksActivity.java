package com.glooory.calligraphy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.callbacks.FragmentRefreshListener;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.fragments.WorksRCFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/11/5 0005 15:20.
 */

public class WorksActivity extends AppCompatActivity implements FragmentRefreshListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar_works)
    Toolbar mToolbar;
    @BindView(R.id.tablayout_works)
    TabLayout mTablayout;
    @BindView(R.id.container_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.appbar_layout_works)
    AppBarLayout mAppbar;

    private WorksRCFragment mCalliFragment;
    private WorksRCFragment mFlourishingFragment;
    private String[] mTabTitles = new String[]{};
    private WorksPagerAdapter mAdapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, WorksActivity.class);
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
        setContentView(R.layout.activity_works_rc);
        ButterKnife.bind(this);
        mTabTitles = getResources().getStringArray(R.array.works_title);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.works));

        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.red_g_i), getResources().getColor(R.color.yellow_g_i),
                getResources().getColor(R.color.blue_g_i), getResources().getColor(R.color.green_g_i));
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setRefreshing(true);

        mAdapter = new WorksPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);

        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int distance = -verticalOffset;
                if (distance > mAppbar.getTotalScrollRange() / 2) {
                    mToolbar.setAlpha(0f);
                } else {
                    mToolbar.setAlpha(1f);
                }

            }
        });
    }

    @Override
    public void requestRefresh() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void requestRefreshDone() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        requestFragmentRefresh();
    }

    /**
     * 刷新 viewpager 的 currentItem
     */
    private void requestFragmentRefresh() {

        int currentIndex = mViewPager.getCurrentItem();
        switch (currentIndex) {
            case 0:
                if (mCalliFragment != null) {
                    mCalliFragment.refreshData();
                }
                break;
            case 1:
                if (mFlourishingFragment != null) {
                    mFlourishingFragment.refreshData();
                }
                break;
        }

    }

    public class WorksPagerAdapter extends FragmentPagerAdapter {

        public WorksPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return WorksRCFragment.newInstanse(Constants.WORKS_NORMAL_INDEX);
                case 1:
                    return WorksRCFragment.newInstanse(Constants.WORKS_FLOURISHING_INDEX);
            }
            return null;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
            switch (position) {
                case 0:
                    mCalliFragment = (WorksRCFragment) createdFragment;
                    break;
                case 1:
                    mFlourishingFragment = (WorksRCFragment) createdFragment;
                    break;
            }
            return createdFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }
}
