package com.glooory.calligraphy.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.glooory.calligraphy.BuildConfig;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.entity.VersionInfoBean;
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
 * Created by Glooory on 2016/5/9 0015.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.card_italy)
    CardView mItalyCard;
    @BindView(R.id.card_roundhand)
    CardView mRoundhandCard;
    @BindView(R.id.card_handprinted)
    CardView mHandprintedCard;
    @BindView(R.id.card_copperplate)
    CardView mCopperplateCard;
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;

    private static final int EXTERNAL_REQUEST_CODE = 33;
    private VersionInfoBean mVersionInfoBean;
    private long exitTime = 0;
    //请求权限的监听
    private RationaleListener mRationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            new AlertDialog.Builder(MainActivity.this)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        checkUpdate();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);

        mRoundhandCard.setOnClickListener(this);
        mItalyCard.setOnClickListener(this);
        mHandprintedCard.setOnClickListener(this);
        mCopperplateCard.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    if (Build.VERSION.SDK_INT >= 21) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calligraphy) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_CALLIGRAPHY);
        } else if (id == R.id.nav_other_font) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_OTHER_FONTS);
        } else if (id == R.id.nav_flourishing) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_FLOURISHING);
        } else if (id == R.id.nav_works) {
            WorksActivity.launch(MainActivity.this);
        } else if (id == R.id.nav_about) {
            UniversalActivity.launch(MainActivity.this, UniversalActivity.CONTENT_ABOUT);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_italy:
                launchFontActivity(FontActivity.FONT_ITALIC);
                break;
            case R.id.card_roundhand:
                launchFontActivity(FontActivity.FONT_ROUND_HAND);
                break;
            case R.id.card_handprinted:
                launchFontActivity(FontActivity.FONT_HAND_PRINTED);
                break;
            case R.id.card_copperplate:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sorry").setMessage("Copperplate is coming soon...")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    private void launchFontActivity(int fontType) {
        FontActivity.launch(MainActivity.this, fontType);
    }

    private void checkUpdate() {
        if (!NetworkUtil.isOnline(this)) {
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
                        }
                    }
                });
        addSubscription(s);
    }

    private boolean isShowUpdateDialog(VersionInfoBean bean) {
        return bean.getVersioncode() > BuildConfig.VERSION_CODE;
    }

    public void showUpdateDialog(final VersionInfoBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.new_version_available)
                .setMessage(String.format(getString(R.string.new_version_des), bean.getVersionname(), bean.getReleaseinfo(), bean.getSize()))
                .setCancelable(false)
                .setPositiveButton(R.string.download_new_version, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            AndPermission.with(MainActivity.this)
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
        UpdateService.launch(MainActivity.this,
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
