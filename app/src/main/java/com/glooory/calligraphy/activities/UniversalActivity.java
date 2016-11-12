package com.glooory.calligraphy.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.glooory.calligraphy.BuildConfig;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.entity.VersionInfoBean;
import com.glooory.calligraphy.fragments.AboutFragment;
import com.glooory.calligraphy.fragments.CalligraphyFragment;
import com.glooory.calligraphy.fragments.FlourishingFragment;
import com.glooory.calligraphy.fragments.OtherfontsFragment;
import com.glooory.calligraphy.net.UpdateRequest;
import com.glooory.calligraphy.service.UpdateService;
import com.glooory.calligraphy.utils.NetworkUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Glooory on 2016/5/13 0013.
 */
public class UniversalActivity extends BaseActivity {
    public static final String CONTENT_TYPE_INDEX = "content_type";
    public static final int CONTENT_CALLIGRAPHY = 11;
    public static final int CONTENT_OTHER_FONTS = 12;
    public static final int CONTENT_FLOURISHING = 13;
    public static final int CONTENT_ABOUT = 14;
    private static final String STATE_FRAG = "state_frag";
    private static final int EXTERNAL_REQUEST_CODE = 33;
    private VersionInfoBean mVersionInfoBean;
    //请求权限的监听
    private RationaleListener mRationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            new AlertDialog.Builder(UniversalActivity.this)
                    .setTitle(R.string.external_permission_tip)
                    .setMessage(R.string.external_permission_des)
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })
                    .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    };

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
            case CONTENT_ABOUT:
                getSupportActionBar().setTitle(R.string.nav_about);
                fragment = AboutFragment.newInstance();
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

    public void checkUpdate() {
        if (!NetworkUtil.isOnline(UniversalActivity.this)) {
            return;
        }
        Subscription s = UpdateRequest.getUpdateApi()
                .checkUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VersionInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                    }

                    @Override
                    public void onNext(VersionInfoBean bean) {
                        if (isShowUpdateDialog(bean)) {
                            showUpdateDialog(bean);
                            mVersionInfoBean = bean;
                        } else {
                            Toast.makeText(getApplication(), R.string.is_newest_version, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        addSubscription(s);
    }

    private boolean isShowUpdateDialog(VersionInfoBean bean) {
        return bean.getVersioncode() > BuildConfig.VERSION_CODE;
    }

    public void showUpdateDialog(final VersionInfoBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UniversalActivity.this)
                .setTitle(R.string.new_version_available)
                .setMessage(String.format(getString(R.string.new_version_des), bean.getVersionname(), bean.getReleaseinfo(), bean.getSize()))
                .setCancelable(false)
                .setPositiveButton(R.string.download_new_version, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            AndPermission.with(UniversalActivity.this)
                                    .requestCode(EXTERNAL_REQUEST_CODE)
                                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    .rationale(mRationaleListener)
                                    .send();
                        } else {
                            actionDownload(mVersionInfoBean);
                        }
                    }
                })
                .setNegativeButton(R.string.cancle, null);
        builder.create().show();
    }

    private void actionDownload(VersionInfoBean bean) {
        UpdateService.launch(UniversalActivity.this,
                bean.getFilename());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 请求获取 External 权限成功的回调
     */
    @PermissionYes(EXTERNAL_REQUEST_CODE)
    private void getWriteExternalYes() {
        actionDownload(mVersionInfoBean);
    }

    /**
     * 请求读写 External 权限失败的回调
     */
    @PermissionNo(EXTERNAL_REQUEST_CODE)
    private void getWriteExternalNo() {
        Toast.makeText(getApplicationContext(), R.string.external_permission_failed, Toast.LENGTH_LONG).show();
    }
}
