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
 * Created by Glooo on 2016/5/13 0013.
 */
public class OtherfontsFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.image_view_font_op)
    ImageView mImageViewOp;
    @BindView(R.id.card_op)
    CardView mCardOp;
    @BindView(R.id.image_view_font_ss)
    ImageView mImageViewSs;
    @BindView(R.id.card_spen)
    CardView mCardSs;
    @BindView(R.id.image_view_font_bp)
    ImageView mImageViewBp;
    @BindView(R.id.card_bp)
    CardView mCardBp;
    @BindView(R.id.image_view_font_gothic)
    ImageView mImageViewGothic;
    @BindView(R.id.card_gothic)
    CardView mCardGothic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.other_fonts, container, false);
        ButterKnife.bind(this, rootView);
        mCardOp.setOnClickListener(this);
        mCardSs.setOnClickListener(this);
        mCardBp.setOnClickListener(this);
        mCardGothic.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_op:
                startImgDetailActivity(mImageViewOp, R.drawable.op_640);
                break;
            case R.id.card_spen:
                startImgDetailActivity(mImageViewSs, R.drawable.spen_orignal);
                break;
            case R.id.card_bp:
                startImgDetailActivity(mImageViewBp, R.drawable.bp_640);
                break;
            case R.id.card_gothic:
                startImgDetailActivity(mImageViewGothic, R.drawable.gothic_480);
                break;
        }
    }
}
