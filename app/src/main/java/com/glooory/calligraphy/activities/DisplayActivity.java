package com.glooory.calligraphy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.views.PinchImageView;

/**
 * Created by Glooo on 2016/5/16 0016.
 */
public class DisplayActivity extends BaseActivity {
    private PinchImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display);

        imageView = (PinchImageView) findViewById(R.id.activity_display_pinchimageview);
        int imageId = getIntent().getIntExtra(Constants.IMAGE_RES_ID, 233);
        imageView.setImageResource(imageId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishThis();
            }
        });
    }

    public void finishThis() {
        finishSelf();
    }
}
