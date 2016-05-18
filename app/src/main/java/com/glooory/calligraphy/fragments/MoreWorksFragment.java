package com.glooory.calligraphy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.ImagePagerActivity;
import com.glooory.calligraphy.adapters.GridViewAdapter;

/**
 * Created by Glooo on 2016/5/15 0015.
 */
public class MoreWorksFragment extends Fragment {

    private AbsListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_view, container, false);
        listView = (GridView) rootView.findViewById(R.id.gridview);
        ((GridView) listView).setAdapter(new GridViewAdapter(getActivity(), GridViewAdapter.WORKS_GRIDVIEW));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2016/5/16 0016
                Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
                intent.putExtra(Constants.IMAGE_PAGER_INDEX, Constants.WORKS_IMAGE_INDEX);
                intent.putExtra(Constants.IMAGE_POSITION, position);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
}
