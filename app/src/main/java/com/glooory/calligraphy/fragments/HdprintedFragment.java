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
 * Created by Glooo on 2016/5/11 0011.
 */
public class HdprintedFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.handprinted_tip_1_a_image)
    ImageView mTipOneAImage;
    @BindView(R.id.handprinted_tip_1_b_image)
    ImageView mTipOneBImage;
    @BindView(R.id.handprinted_tip_1_c_image)
    ImageView mTipOneCImage;
    @BindView(R.id.handprinted_tip_1_d_image)
    ImageView mTipOneDImage;
    @BindView(R.id.handprinted_tip_2_image)
    ImageView mTipTwoImage;
    @BindView(R.id.handprinted_tip_3_a_image)
    ImageView mTipThreeAImage;
    @BindView(R.id.handprinted_tip_3_b_image)
    ImageView mTipThreeBImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.handprinted_tips, container, false);
        ButterKnife.bind(this, rootView);
        mTipOneAImage.setOnClickListener(this);
        mTipOneBImage.setOnClickListener(this);
        mTipOneCImage.setOnClickListener(this);
        mTipOneDImage.setOnClickListener(this);
        mTipTwoImage.setOnClickListener(this);
        mTipThreeAImage.setOnClickListener(this);
        mTipThreeBImage.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handprinted_tip_1_a_image:
                startImgDetailActivity(mTipOneAImage, R.drawable.handprinted_tip_1_a_480);
                break;
            case R.id.handprinted_tip_1_b_image:
                startImgDetailActivity(mTipOneBImage, R.drawable.handprinted_tip_1_b_480);
                break;
            case R.id.handprinted_tip_1_c_image:
                startImgDetailActivity(mTipOneCImage, R.drawable.handprinted_tip_1_c_480);
                break;
            case R.id.handprinted_tip_1_d_image:
                startImgDetailActivity(mTipOneDImage, R.drawable.handprinted_tip_1_d_480);
                break;
            case R.id.handprinted_tip_2_image:
                startImgDetailActivity(mTipTwoImage, R.drawable.handprinted_tip_2_480);
                break;
            case R.id.handprinted_tip_3_a_image:
                startImgDetailActivity(mTipThreeAImage, R.drawable.handprinted_tip_3_a_480);
                break;
            case R.id.handprinted_tip_3_b_image:
                startImgDetailActivity(mTipThreeBImage, R.drawable.handprinted_tip_3_b_480);
                break;
        }
    }
}
