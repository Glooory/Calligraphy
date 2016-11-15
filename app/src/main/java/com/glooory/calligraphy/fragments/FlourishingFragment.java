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
 * Created by Glooory on 2016/5/13 0013.
 */
public class FlourishingFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.flourishing_image_a)
    ImageView mFlourishingImageA;
    @BindView(R.id.flourishing_image_b)
    ImageView mFlourishingImageB;

    public static FlourishingFragment newInstance() {
        return new FlourishingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flourishing, container, false);
        ButterKnife.bind(this, rootView);
        mFlourishingImageA.setOnClickListener(this);
        mFlourishingImageB.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flourishing_image_a:
                startImgDetailActivity(mFlourishingImageA, R.drawable.flourishing_a_480);
                break;
            case R.id.flourishing_image_b:
                startImgDetailActivity(mFlourishingImageB, R.drawable.flourishing_b_orignal);
                break;
        }
    }
}
