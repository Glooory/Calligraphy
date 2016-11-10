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
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.italic_tip_3_image)
    ImageView mTipThreeImage;
    @BindView(R.id.italic_tip_4_a_image)
    ImageView mTipFourAImage;
    @BindView(R.id.italic_tip_4_b_image)
    ImageView mTipFourBImage;
    @BindView(R.id.italic_tip_4_c_image)
    ImageView mTipFourCImage;
    @BindView(R.id.italic_tip_4_d_image)
    ImageView mTipFourDImage;
    @BindView(R.id.italic_tip_4_e_image)
    ImageView mTipFourEImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.italic_tips, container, false);
        ButterKnife.bind(this, rootView);
        mTipThreeImage.setOnClickListener(this);
        mTipFourAImage.setOnClickListener(this);
        mTipFourBImage.setOnClickListener(this);
        mTipFourCImage.setOnClickListener(this);
        mTipFourDImage.setOnClickListener(this);
        mTipFourEImage.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.italic_tip_3_image:
                startImgDetailActivity(mTipThreeImage, R.drawable.italy_f_640);
                break;
            case R.id.italic_tip_4_a_image:
                startImgDetailActivity(mTipFourAImage, R.drawable.italy_tip_4_a_480);
                break;
            case R.id.italic_tip_4_b_image:
                startImgDetailActivity(mTipFourBImage, R.drawable.italy_tip_4_b_480);
                break;
            case R.id.italic_tip_4_c_image:
                startImgDetailActivity(mTipFourCImage, R.drawable.italy_tip_4_c_480);
                break;
            case R.id.italic_tip_4_d_image:
                startImgDetailActivity(mTipFourDImage, R.drawable.italy_tip_4_d_480);
                break;
            case R.id.italic_tip_4_e_image:
                startImgDetailActivity(mTipFourEImage, R.drawable.italy_tip_4_e_480);
                break;
        }
    }
}
