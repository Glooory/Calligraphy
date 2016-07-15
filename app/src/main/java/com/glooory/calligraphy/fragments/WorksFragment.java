package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.adapters.WorksAdapter;
import com.glooory.calligraphy.views.SpaceItemDecoration;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class WorksFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private WorksAdapter mAdapter;
    private int worksIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        worksIndex = getArguments().getInt(Constants.WORKS_INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mAdapter = new WorksAdapter(getActivity(), worksIndex);
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
        mRecyclerView.setPadding(8, 0, 8, 0);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

}
