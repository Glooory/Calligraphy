package com.glooory.calligraphy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.CalligraphyFragment;
import com.glooory.calligraphy.fragments.FlourishingFragment;
import com.glooory.calligraphy.fragments.OtherfontsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/5/13 0013.
 */
public class UniversalActivity extends BaseActivity {
    public static final String CONTENT_TYPE_INDEX = "content_type";
    public static final int CONTENT_CALLIGRAPHY = 11;
    public static final int CONTENT_OTHER_FONTS = 12;
    public static final int CONTENT_FLOURISHING = 13;
    private static final String STATE_FRAG = "state_frag";

    @BindView(R.id.toolbar_universal)
    Toolbar mToolbar;

    Fragment fragment = null;

    public static void launch(Activity activity, int contentTypeIndex) {
        Intent intent = new Intent(activity, UniversalActivity.class);
        intent.putExtra(CONTENT_TYPE_INDEX, contentTypeIndex);
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
        setContentView(R.layout.activity_universal);
        ButterKnife.bind(this);
        setupToolbar();
        if (savedInstanceState != null) {
            fragment = getFragment(savedInstanceState.getInt(STATE_FRAG));
        } else {
            int type = getIntent().getIntExtra(CONTENT_TYPE_INDEX, CONTENT_CALLIGRAPHY);
            fragment = getFragment(type);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container_universal, fragment).commit();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Fragment getFragment(int type) {
        switch (type) {
            case CONTENT_CALLIGRAPHY:
                fragment = CalligraphyFragment.newInstance();
                getSupportActionBar().setTitle(R.string.calligraphy);
                return fragment;
            case CONTENT_OTHER_FONTS:
                fragment = OtherfontsFragment.newInstance();
                getSupportActionBar().setTitle(R.string.other_font);
                return fragment;
            case CONTENT_FLOURISHING:
                getSupportActionBar().setTitle(R.string.flourishing);
                fragment = FlourishingFragment.newInstance();
                return fragment;
        }
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        int type = 0;
        if (fragment instanceof CalligraphyFragment) {
            type = CONTENT_CALLIGRAPHY;
        } else if (fragment instanceof OtherfontsFragment) {
            type = CONTENT_OTHER_FONTS;
        } else if (fragment instanceof FlourishingFragment) {
            type = CONTENT_FLOURISHING;
        }
        outState.putInt(STATE_FRAG, type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (fragment instanceof FlourishingFragment) {
            getMenuInflater().inflate(R.menu.menu_flourishing, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_flourishing_more) {
            WorksActivity.launch(this);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
                return true;
            } else {
                super.onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
