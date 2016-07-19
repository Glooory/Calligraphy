package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

/**
 * Created by Glooo on 2016/5/11 0011.
 */
public class HdprintedTipsFragment extends BaseTipsFragment implements View.OnClickListener {
    private ImageView tip1ImageA;
    private ImageView tip1ImageB;
    private ImageView tip1ImageC;
    private ImageView tip1ImageD;
    private ImageView tip2Image;
    private ImageView tip3ImageA;
    private ImageView tip3ImageB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.handprinted_tips, container, false);

        tip1ImageA = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_a_image);
        tip1ImageB = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_b_image);
        tip1ImageC = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_c_image);
        tip1ImageD = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_d_image);
        tip2Image = (ImageView) rootView.findViewById(R.id.handprinted_tip_2_image);
        tip3ImageA = (ImageView) rootView.findViewById(R.id.handprinted_tip_3_a_image);
        tip3ImageB = (ImageView) rootView.findViewById(R.id.handprinted_tip_3_b_image);

        tip1ImageA.setOnClickListener(this);
        tip1ImageB.setOnClickListener(this);
        tip1ImageC.setOnClickListener(this);
        tip1ImageD.setOnClickListener(this);
        tip2Image.setOnClickListener(this);
        tip3ImageA.setOnClickListener(this);
        tip3ImageB.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handprinted_tip_1_a_image:
                startImgActivity(tip1ImageA, R.drawable.handprinted_tip_1_a_480);
                break;
            case R.id.handprinted_tip_1_b_image:
                startImgActivity(tip1ImageB, R.drawable.handprinted_tip_1_b_480);
                break;
            case R.id.handprinted_tip_1_c_image:
                startImgActivity(tip1ImageC, R.drawable.handprinted_tip_1_c_480);
                break;
            case R.id.handprinted_tip_1_d_image:
                startImgActivity(tip1ImageD, R.drawable.handprinted_tip_1_d_480);
                break;
            case R.id.handprinted_tip_2_image:
                startImgActivity(tip2Image, R.drawable.handprinted_tip_2_480);
                break;
            case R.id.handprinted_tip_3_a_image:
                startImgActivity(tip3ImageA, R.drawable.handprinted_tip_3_a_480);
                break;
            case R.id.handprinted_tip_3_b_image:
                startImgActivity(tip3ImageB, R.drawable.handprinted_tip_3_b_480);
                break;
        }
    }
}
