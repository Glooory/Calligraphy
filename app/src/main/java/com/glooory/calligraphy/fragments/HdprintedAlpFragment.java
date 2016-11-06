package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.adapters.CharAdapter;

/**
 * Created by Glooo on 2016/5/11 0011.
 */
public class HdprintedAlpFragment extends BaseAlpFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recycer_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CharAdapter(getActivity(), Constants.FONT_HANDPRINTED);
        mRecyclerView.setAdapter(mAdapter);
        return layout;
    }

}
