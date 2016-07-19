package com.glooory.calligraphy.fragments;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.DisplayActivity;

/**
 * Created by Glooo on 2016/7/19 0019.
 */
public class BaseTipsFragment extends Fragment {

    public void startImgActivity(ImageView imageView, int imgResId) {
        Intent intent = new Intent(getActivity(), DisplayActivity.class);
        intent.putExtra(Constants.IMAGE_RES_ID, imgResId);
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setTransitionName(getString(R.string.transition_name));
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(), imageView, getString(R.string.transition_name)).toBundle());
        } else {
            startActivity(intent);
        }
    }

}
