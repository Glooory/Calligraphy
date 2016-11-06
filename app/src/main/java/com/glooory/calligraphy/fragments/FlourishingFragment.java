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
import android.widget.ImageView;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.DisplayActivity;

/**
 * Created by Glooo on 2016/5/13 0013.
 */
public class FlourishingFragment extends Fragment implements View.OnClickListener {
    private ImageView imageA;
    private ImageView imageB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.flourishing, container, false);

        imageA = (ImageView) rootView.findViewById(R.id.flourishing_image_a);
        imageB = (ImageView) rootView.findViewById(R.id.flourishing_image_b);
        imageA.setOnClickListener(this);
        imageB.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flourishing_image_a:
                Intent intent1 = new Intent(getActivity(), DisplayActivity.class);
                intent1.putExtra(Constants.IMAGE_RES_ID, R.drawable.flourishing_a_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    imageA.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent1, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), imageA, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent1);
                }
                break;
            case R.id.flourishing_image_b:
                Intent intent2 = new Intent(getActivity(), DisplayActivity.class);
                intent2.putExtra(Constants.IMAGE_RES_ID, R.drawable.flourishing_b_orignal);
                if (Build.VERSION.SDK_INT >= 21) {
                    imageB.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), imageB, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent2);
                }
                break;
        }
    }
}
