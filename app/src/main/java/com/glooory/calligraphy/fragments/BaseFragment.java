package com.glooory.calligraphy.fragments;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.glooory.calligraphy.activities.ImageDetailActivity;

/**
 * Created by Glooo on 2016/7/19 0019.
 */
public class BaseFragment extends Fragment {

    public void startImgDetailActivity(ImageView imageView, int imgResId) {
        ImageDetailActivity.launch(getActivity(), imageView, imgResId);
    }

}
