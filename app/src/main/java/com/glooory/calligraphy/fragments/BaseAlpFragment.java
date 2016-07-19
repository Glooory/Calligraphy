package com.glooory.calligraphy.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.glooory.calligraphy.adapters.CharAdapter;

/**
 * Created by Glooo on 2016/7/18 0018.
 */
public class BaseAlpFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected CharAdapter mAdapter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clearMemoryCache();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

}
