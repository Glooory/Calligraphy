package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.R;

/**
 * Created by Glooory on 2016/5/13 0013.
 */
public class CalligraphyFragment extends BaseFragment {

    public static CalligraphyFragment newInstance() {
        return new CalligraphyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calligraphy, container, false);
    }
}
