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
 * Created by Glooo on 2016/5/11 0011.
 */
public class HdprintedTipsFragment extends Fragment implements View.OnClickListener {
    private ImageView tip1ImageA;
    private ImageView tip1ImageB;
    private ImageView tip1ImageC;
    private ImageView tip1ImageD;
    private ImageView tip2Image;
    private ImageView tip3ImageA;
    private ImageView tip3ImageB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.handprinted_tips, container, false);

        tip1ImageA = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_a_image);
        tip1ImageB = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_b_image);
        tip1ImageC = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_c_image);
        tip1ImageD = (ImageView) rootView.findViewById(R.id.handprinted_tip_1_d_image);
        tip2Image = (ImageView) rootView.findViewById(R.id.handprinted_tip_2_image);
        tip3ImageA = (ImageView) rootView.findViewById(R.id.handprinted_tip_3_a_image);
        tip3ImageB = (ImageView) rootView.findViewById(R.id.handprinted_tip_3_b_image);

        tip1ImageA.setOnClickListener(this);
        tip1ImageB.setOnClickListener(this);
        tip1ImageC.setOnClickListener(this);
        tip1ImageD.setOnClickListener(this);
        tip2Image.setOnClickListener(this);
        tip3ImageA.setOnClickListener(this);
        tip3ImageB.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handprinted_tip_1_a_image:
                Intent intent1 = new Intent(getActivity(), DisplayActivity.class);
                intent1.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_1_a_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip1ImageA.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent1, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip1ImageA, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent1);
                }
                break;
            case R.id.handprinted_tip_1_b_image:
                Intent intent2 = new Intent(getActivity(), DisplayActivity.class);
                intent2.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_1_b_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip1ImageB.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip1ImageB, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent2);
                }
                break;
            case R.id.handprinted_tip_1_c_image:
                Intent intent3 = new Intent(getActivity(), DisplayActivity.class);
                intent3.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_1_c_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip1ImageC.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent3, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip1ImageC, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent3);
                }
                break;
            case R.id.handprinted_tip_1_d_image:
                Intent intent4 = new Intent(getActivity(), DisplayActivity.class);
                intent4.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_1_d_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip1ImageD.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent4, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip1ImageD, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent4);
                }
                break;
            case R.id.handprinted_tip_2_image:
                Intent intent5 = new Intent(getActivity(), DisplayActivity.class);
                intent5.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_2_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip2Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent5, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip2Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent5);
                }
                break;
            case R.id.handprinted_tip_3_a_image:
                Intent intent6 = new Intent(getActivity(), DisplayActivity.class);
                intent6.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_3_a_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip3ImageA.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent6, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip3ImageA, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent6);
                }
                break;
            case R.id.handprinted_tip_3_b_image:
                Intent intent7 = new Intent(getActivity(), DisplayActivity.class);
                intent7.putExtra(Constants.IMAGE_RES_ID, R.drawable.handprinted_tip_3_b_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip3ImageB.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent7, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), tip3ImageB, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent7);
                }
                break;
        }
    }
}
