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
 * Created by Glooory on 2016/5/12 0012.
 */
public class HdprintedDesFragment extends BaseFragment {
    @BindView(R.id.image_view_handprinted_des)
    ImageView mImageViewExample;
    @BindView(R.id.handprinted_des_image_card)
    CardView mDesCard;

    public static HdprintedDesFragment newInstance() {
        return new HdprintedDesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_handprinted_des, container, false);
        ButterKnife.bind(this, rootView);
        mDesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImgDetailActivity(mImageViewExample, R.drawable.handprinted_header_480);
            }
        });

        return rootView;
    }
}
