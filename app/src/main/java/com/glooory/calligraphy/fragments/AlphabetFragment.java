package com.glooory.calligraphy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.FontActivity;
import com.glooory.calligraphy.adapters.CharAdapter;

/**
 * Created by Glooory on 2016/11/11 0011 14:23.
 */

public class AlphabetFragment extends BaseFragment {
    private Context mContext;
    private int mFontType;
    private RecyclerView mRecyclerView;
    private CharAdapter mAdapter;

    public static AlphabetFragment newInstance(int fontType) {
        Bundle args = new Bundle();
        args.putInt(FontActivity.FONT_TYPE_INDEX, fontType);
        AlphabetFragment fragment = new AlphabetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFontType = getArguments().getInt(FontActivity.FONT_TYPE_INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CharAdapter(mContext, mFontType);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }
}
