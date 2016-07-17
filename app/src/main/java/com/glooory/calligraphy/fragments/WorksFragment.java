package com.glooory.calligraphy.fragments;

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

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.Utils.FileUtil;
import com.glooory.calligraphy.Utils.NetworkUtil;
import com.glooory.calligraphy.activities.WorksActivity;
import com.glooory.calligraphy.adapters.WorksAdapter;
import com.glooory.calligraphy.modul.CalliWork;
import com.glooory.calligraphy.views.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glooo on 2016/7/13 0013.
 */
public class WorksFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private WorksAdapter mAdapter;
    private int worksIndex;
    private List<CalliWork> mWorks;
    private boolean isFirstTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        worksIndex = getArguments().getInt(Constants.WORKS_INDEX);
        Log.d("WorksFragment", "worksIndex:" + worksIndex);
        mWorks = new ArrayList<>();
        isFirstTime = PreferenceManager.getDefaultSharedPreferences(getContext())
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
        mSwipeLayout.setRefreshing(true);
        if (isFirstTime) {
            httpRequest();
        } else {
            loadFromCache();
        }
        mAdapter = new WorksAdapter(getActivity(), worksIndex);
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

    private void httpRequest() {
        if (worksIndex == Constants.WORKS_FLOURISHING_INDEX) {
            Thread subThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    requestFloFromNetwork();
                    if (isFirstTime) {
                        requestNorFromNetWork();
                    }
                    loadFlo();
                }
            });
            joinThread(subThread);
        } else {
            Thread subThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    requestNorFromNetWork();
                    if (isFirstTime) {
                        requestFloFromNetwork();
                    }
                    loadNor();
                }
            });
            joinThread(subThread);
        }
    }

    private void loadFromCache() {
        if (worksIndex == Constants.WORKS_FLOURISHING_INDEX) {
            Thread subThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    loadFlo();
                }
            });
            joinThread(subThread);
        } else {
            Thread subThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    loadNor();
                }
            });
            joinThread(subThread);
        }
    }

    private void requestNorFromNetWork() {
        String resA = NetworkUtil.loadPins(Constants.NOR_WORKS_URLS_A);
        FileUtil.savePins(getContext(), resA, Constants.NOR_WORKS_PINID_A);
        String resB = NetworkUtil.loadPins(Constants.NOR_WORKS_URLS_B);
        FileUtil.savePins(getContext(), resB, Constants.NOR_WORKS_PINID_B);
    }

    private void loadNor() {
        mWorks.clear();
        NetworkUtil.parseWorks(FileUtil.readPins(getContext(), Constants.NOR_WORKS_PINID_A), mWorks);
        NetworkUtil.parseWorks(FileUtil.readPins(getContext(), Constants.NOR_WORKS_PINID_B), mWorks);
    }

    private void requestFloFromNetwork() {
        String res = NetworkUtil.loadPins(Constants.FLO_WORKS_URLS);
        FileUtil.savePins(getContext(), res, Constants.FLO_WORKS_PINID);
    }

    private void loadFlo() {
        mWorks.clear();
        NetworkUtil.parseWorks(FileUtil.readPins(getContext(), Constants.FLO_WORKS_PINID), mWorks);
    }

    private void joinThread(Thread thread) {
        try {
            thread.start();
            thread.join();
            mSwipeLayout.setRefreshing(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        httpRequest();
        mAdapter.setWorkList(mWorks);
    }
}
