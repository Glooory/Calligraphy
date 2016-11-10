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
public class ItalicDesFragment extends BaseFragment {
    @BindView(R.id.italic_des_image)
    ImageView mItalicExampleImage;
    @BindView(R.id.italic_des_image_card)
    CardView mItalicDesCard;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.italic_des, container, false);
        ButterKnife.bind(this, rootView);
        mItalicDesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImgDetailActivity(mItalicExampleImage, R.drawable.italy_header_480);
            }
        });
        return rootView;
    }
}
