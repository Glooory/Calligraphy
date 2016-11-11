package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooory on 2016/5/9 0009.
 */
public class RdhandTipsFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.roundhand_tip_1_image)
    ImageView mTipOneImage;
    @BindView(R.id.roundhand_tip_2_image)
    ImageView mTipTwoImage;
    @BindView(R.id.roundhand_tip_3_image)
    ImageView mTipThreeImage;
    @BindView(R.id.roundhand_tip_4_image)
    ImageView mTipFourImage;

    public static RdhandTipsFragment newInstance() {
        return new RdhandTipsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roundhand_tips, container, false);
        ButterKnife.bind(this, rootView);
        mTipOneImage.setOnClickListener(this);
        mTipTwoImage.setOnClickListener(this);
        mTipThreeImage.setOnClickListener(this);
        mTipFourImage.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roundhand_tip_1_image:
                startImgDetailActivity(mTipOneImage, R.drawable.roundhand_tip_image_1_480);
                break;
            case R.id.roundhand_tip_2_image:
                startImgDetailActivity(mTipTwoImage, R.drawable.roundhand_tip_image_2_480);
                break;
            case R.id.roundhand_tip_3_image:
                startImgDetailActivity(mTipThreeImage, R.drawable.roundhand_tip_image_3_480);
                break;
            case R.id.roundhand_tip_4_image:
                startImgDetailActivity(mTipFourImage, R.drawable.roundhand_tip_image_4_480);
                break;
        }
    }
}
