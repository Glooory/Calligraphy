package com.glooory.calligraphy.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.fragments.CalligraphyFragment;
import com.glooory.calligraphy.fragments.FlourishingFragment;
import com.glooory.calligraphy.fragments.OtherfontsFragment;

/**
 * Created by Glooo on 2016/5/13 0013.
 */
public class UniversalActivity extends AppCompatActivity {
    public static final String STATE_FRAG = "state_frag";
    private Toolbar mToolbar;
    Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal);

        if (savedInstanceState != null) {
            fragment = getFragment(savedInstanceState.getInt(STATE_FRAG));
        } else {
            int type = getIntent().getIntExtra(Constants.FRAGMENT_INDEX, Constants.CALLIFRAG);
            fragment = getFragment(type);
        }

        setupToolbar();

        getSupportFragmentManager().beginTransaction().replace(R.id.universal_content, fragment).commit();

    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.universal_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Fragment getFragment(int type) {
        switch (type) {
            case Constants.CALLIFRAG:
                fragment = new CalligraphyFragment();
                this.setTitle("英文书法");
                return fragment;
            case Constants.OTHERFRAG:
                fragment = new OtherfontsFragment();
                this.setTitle("其他字体");
                return fragment;
            case Constants.FLOURIFRAG:
                this.setTitle("装饰花边");
                fragment = new FlourishingFragment();
                return fragment;
        }
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        int type = 100;
        if (fragment instanceof CalligraphyFragment) {
            type = 0;
        } else if (fragment instanceof OtherfontsFragment) {
            type = 1;
        } else if (fragment instanceof FlourishingFragment) {
            type = 2;
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
            Intent intent = new Intent(UniversalActivity.this, GridviewActivity.class);
            intent.putExtra(Constants.FRAGMENT_INDEX, Constants.FLOURISHINGWORKS);
            if (Build.VERSION.SDK_INT >= 21) {
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(UniversalActivity.this).toBundle());
            } else {
                startActivity(intent);
            }
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

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}
