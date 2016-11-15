package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glooory.calligraphy.BuildConfig;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.UniversalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Glooory on 2016/11/12 0012 20:19.
 */

public class AboutFragment extends BaseFragment {

    @BindView(R.id.tv_current_version)
    TextView mVersionTv;
    @BindView(R.id.rl_check_update)
    RelativeLayout mCheckUpdateRl;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, rootView);
        mVersionTv.setText("当前版本：" + BuildConfig.VERSION_NAME);
        mCheckUpdateRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UniversalActivity) getActivity()).checkUpdate();
            }
        });
        return rootView;
    }
}
