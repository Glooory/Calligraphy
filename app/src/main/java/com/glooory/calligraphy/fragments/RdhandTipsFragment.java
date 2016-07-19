package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

/**
 * Created by Glooo on 2016/5/9 0009.
 */
public class RdhandTipsFragment extends BaseTipsFragment implements View.OnClickListener {
    private ImageView tip1Image;
    private ImageView tip2Image;
    private ImageView tip3Image;
    private ImageView tip4Image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roundhand_tips, container, false);

        tip1Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_1_image);
        tip2Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_2_image);
        tip3Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_3_image);
        tip4Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_4_image);
        tip1Image.setOnClickListener(this);
        tip2Image.setOnClickListener(this);
        tip3Image.setOnClickListener(this);
        tip4Image.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roundhand_tip_1_image:
                startImgActivity(tip1Image, R.drawable.roundhand_tip_image_1_480);
                break;
            case R.id.roundhand_tip_2_image:
                startImgActivity(tip2Image, R.drawable.roundhand_tip_image_2_480);
                break;
            case R.id.roundhand_tip_3_image:
                startImgActivity(tip3Image, R.drawable.roundhand_tip_image_3_480);
                break;
            case R.id.roundhand_tip_4_image:
                startImgActivity(tip4Image, R.drawable.roundhand_tip_image_4_480);
                break;
        }
    }
}
