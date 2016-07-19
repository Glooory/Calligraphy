package com.glooory.calligraphy.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.glooory.calligraphy.Callbacks.FileCacheListener;
import com.glooory.calligraphy.Callbacks.HttpCallbackListener;
import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.FileUtil;
import com.glooory.calligraphy.Utils.NetworkUtil;
import com.glooory.calligraphy.activities.WorksActivity;
import com.glooory.calligraphy.adapters.WorksAdapter;
import com.glooory.calligraphy.modul.CalliWork;
import com.glooory.calligraphy.views.SpaceItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class WorksFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener,
        HttpCallbackListener, FileCacheListener {
    private Context mContext;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private WorksAdapter mAdapter;
    private int worksIndex;
    public static List<CalliWork> mWorks = new ArrayList<>();
    private boolean isFirstTime;

    public static WorksFragment newInstance(int type) {
        WorksFragment fragment = new WorksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.WORKS_INDEX, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        worksIndex = getArguments().getInt(Constants.WORKS_INDEX);
        Log.d("WorksFragment", "worksIndex:" + worksIndex);
        clearWorksList();
        isFirstTime = PreferenceManager.getDefaultSharedPreferences(mContext)
                .getBoolean(WorksActivity.FIRST_TIME, true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swipe_layout, container, false);
        mSwipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycer_view);
        mSwipeLayout.setColorSchemeResources(R.color.color_scheme_1_1, R.color.color_scheme_1_2
                ,R.color.color_scheme_1_3, R.color.color_scheme_1_4);
        mSwipeLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeLayout.setOnRefreshListener(this);
        if (isFirstTime) {
            mSwipeLayout.setRefreshing(true);
        }
        NetworkUtil.loadPins(mContext, this);
        mAdapter = new WorksAdapter(mContext, worksIndex);
        mAdapter.setWorkList(mWorks);
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(16));
        mRecyclerView.setPadding(8, 0, 8, 0);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstTime) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
            editor.putBoolean(WorksActivity.FIRST_TIME, false);
            editor.commit();
        }
    }

    @Override
    public void onRefresh() {
        Logger.d("Fragment中的onRefesh回调方法");
        NetworkUtil.loadPins(mContext, this);
    }


    @Override
    public void readCacheFinish(List<CalliWork> workList) {
//        Logger.d("Fragment中的readCacheFinish回调方法");
        clearWorksList();
        this.mWorks = workList;
        mAdapter.setWorkList(workList);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void readCacheError(Exception e) {
//        Logger.d("Fragment中的readCacheError回调方法");
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onHttpRequestFinish() {
//        Logger.d("Fragment中的HTTP请求成功的回调方法");
        FileUtil.readPins(mContext, worksIndex, this);
//        Logger.d(mContext == null);
    }

    @Override
    public void onHttpRequestError(Exception e) {
//        Logger.d("Fragment中的HTTP请求失败的回调方法");
        Toast.makeText(mContext, "网络不可用", Toast.LENGTH_SHORT).show();
        mSwipeLayout.setRefreshing(false);
    }

    public void clearWorksList() {
        if (mWorks != null) {
            mWorks.clear();
        }
    }
}
