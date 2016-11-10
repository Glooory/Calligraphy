package com.glooory.calligraphy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.net.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class ImageDetailActivity extends BaseActivity {
    private static final String KEY_IMG_RES_ID = "res_id";
    private static final String KEY_IMG_URL_KEY = "url_key";
    public static final String IMG_TYPE_INDEX = "img_type";
    public static final int IMG_TYPE_RES = 47;
    public static final int IMG_TYPE_URL = 58;
    public static final String IMG_TRANSITION_NAME = "imgtran";

    @BindView(R.id.img_image_detail)
    ImageView mImageView;
    @BindView(R.id.toolbar_image_detail)
    Toolbar mToolbar;

    private int mImgType;
    private String mImgUrlRoot;
    private int mImgResId;
    private String mImgUrlKey;
    private PhotoViewAttacher mAttacher;

    public static void launch(Activity activity, ImageView imageView, String imgUrlKey) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(IMG_TYPE_INDEX, IMG_TYPE_URL);
        intent.putExtra(KEY_IMG_URL_KEY, imgUrlKey);
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setTransitionName(IMG_TRANSITION_NAME);
            activity.startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, IMG_TRANSITION_NAME).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    public static void launch(Activity activity, ImageView imageView, int imgResId) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(IMG_TYPE_INDEX, IMG_TYPE_RES);
        intent.putExtra(KEY_IMG_RES_ID, imgResId);
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setTransitionName(IMG_TRANSITION_NAME);
            activity.startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, IMG_TRANSITION_NAME).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);
        mImgType = getIntent().getIntExtra(IMG_TYPE_INDEX, IMG_TYPE_RES);
        initView();
        initData();
        loadImage();
        mAttacher = new PhotoViewAttacher(mImageView);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void initData() {
        if (mImgType == IMG_TYPE_RES) {
            mImgResId = getIntent().getIntExtra(KEY_IMG_RES_ID, 0);
        } else if (mImgType == IMG_TYPE_URL) {
            mImgUrlRoot = getString(R.string.format_url_image_big);
            mImgUrlKey = getIntent().getStringExtra(KEY_IMG_URL_KEY);
        }
    }

    /**
     * 加载图片
     */
    private void loadImage() {
        switch (mImgType) {
            case IMG_TYPE_RES:
                ImageLoader.load(this, mImageView, mImgResId);
                break;
            case IMG_TYPE_URL:
                ImageLoader.load(this, mImageView, String.format(mImgUrlRoot, mImgUrlKey));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mImgType == IMG_TYPE_URL) {
            getMenuInflater().inflate(R.menu.activity_image_detail, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_download_img) {
            actionDownload();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionDownload() {

    }

    public void finishThis() {
        finishSelf();
    }
}
