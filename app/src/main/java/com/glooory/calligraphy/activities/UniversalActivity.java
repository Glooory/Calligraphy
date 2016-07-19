package com.glooory.calligraphy.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.NetworkUtil;
import com.glooory.calligraphy.fragments.CalligraphyFragment;
import com.glooory.calligraphy.fragments.FlourishingFragment;
import com.glooory.calligraphy.fragments.OtherfontsFragment;

/**
 * Created by Glooo on 2016/5/13 0013.
 */
public class UniversalActivity extends BaseActivity {
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
            SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isFirstTime = spf.getBoolean(WorksActivity.FIRST_TIME, true);

            if (isFirstTime && !NetworkUtil.isOnline(this)) {
                Toast.makeText(this, "网络不可用，请检查后重试。", Toast.LENGTH_SHORT).show();
                return true;
            }

            if (isFirstTime && NetworkUtil.isMobileData(this)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("你正在使用的是数据流量，后面需要加载的图片较多，确定继续？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("WorksActivity", "点击了确定");
                                startWorksActivity();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("WorksActivity", "点击了取消");
                                return;
                            }
                        })
                        .setCancelable(false);
                builder.create().show();
            } else {
                startWorksActivity();
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

    private void startWorksActivity() {
        Intent intent = new Intent(UniversalActivity.this, WorksActivity.class);
        intent.putExtra(Constants.WORKS_INDEX, Constants.WORKS_FLOURISHING_INDEX);
        if (Build.VERSION.SDK_INT >= 21) {
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(UniversalActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
