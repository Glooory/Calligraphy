package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glooo on 2016/5/12 0012.
 */
public class RdhandDesFragment extends BaseFragment {
    @BindView(R.id.image_view_roundhand_des)
    ImageView mImageViewExample;
    @BindView(R.id.roundhand_des_image_card)
    CardView mRoundhandDes;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roundhand_des, container, false);
        ButterKnife.bind(this, rootView);
        mRoundhandDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImgDetailActivity(mImageViewExample, R.drawable.roundhand_header_ver_480);
            }
        });
        return rootView;
    }
}
