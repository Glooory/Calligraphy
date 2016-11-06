package com.glooory.calligraphy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.DisplayActivity;

/**
 * Created by Glooo on 2016/5/12 0012.
 */
public class RdhandDesFragment extends Fragment {
    private CardView desImage;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roundhand_des, container, false);
        desImage = (CardView) rootView.findViewById(R.id.roundhand_des_image_card);
        desImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra(Constants.IMAGE_RES_ID, R.drawable.roundhand_header_ver_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    desImage.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), desImage, getString(R.string.transition_name)
                    ).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
}
