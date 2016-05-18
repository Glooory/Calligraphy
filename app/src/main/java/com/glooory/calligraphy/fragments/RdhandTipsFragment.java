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

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.DisplayActivity;

/**
 * Created by Glooo on 2016/5/9 0009.
 */
public class RdhandTipsFragment extends Fragment implements View.OnClickListener {
    private ImageView tip1Image;
    private ImageView tip2Image;
    private ImageView tip3Image;
    private ImageView tip4Image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roundhand_tips, container, false);

        tip1Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_1_image);
        tip2Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_2_image);
        tip3Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_3_image);
        tip4Image = (ImageView) rootView.findViewById(R.id.roundhand_tip_4_image);
        tip1Image.setOnClickListener(this);
        tip2Image.setOnClickListener(this);
        tip3Image.setOnClickListener(this);
        tip4Image.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roundhand_tip_1_image:
                Intent intent1 = new Intent(getActivity(), DisplayActivity.class);
                intent1.putExtra(Constants.IMAGE_RES_ID, R.drawable.roundhand_tip_image_1_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip1Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent1, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip1Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent1);
                }
                break;
            case R.id.roundhand_tip_2_image:
                Intent intent2 = new Intent(getActivity(), DisplayActivity.class);
                intent2.putExtra(Constants.IMAGE_RES_ID, R.drawable.roundhand_tip_image_2_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip2Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip2Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent2);
                }
                break;
            case R.id.roundhand_tip_3_image:
                Intent intent3 = new Intent(getActivity(), DisplayActivity.class);
                intent3.putExtra(Constants.IMAGE_RES_ID, R.drawable.roundhand_tip_image_3_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip3Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent3, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip3Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent3);
                }
                break;
            case R.id.roundhand_tip_4_image:
                Intent intent4 = new Intent(getActivity(), DisplayActivity.class);
                intent4.putExtra(Constants.IMAGE_RES_ID, R.drawable.roundhand_tip_image_4_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent4, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip4Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent4);
                }
                break;
        }
    }
}
