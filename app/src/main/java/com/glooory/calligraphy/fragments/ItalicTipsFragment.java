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
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicTipsFragment extends Fragment implements View.OnClickListener {
    private ImageView tip3Image;
    private ImageView tip4ImageA;
    private ImageView tip4ImageB;
    private ImageView tip4ImageC;
    private ImageView tip4ImageD;
    private ImageView tip4ImageE;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.italic_tips, container, false);

        tip3Image = (ImageView) rootView.findViewById(R.id.italic_tip_3_image);
        tip4ImageA = (ImageView) rootView.findViewById(R.id.italic_tip_4_a_image);
        tip4ImageB = (ImageView) rootView.findViewById(R.id.italic_tip_4_b_image);
        tip4ImageC = (ImageView) rootView.findViewById(R.id.italic_tip_4_c_image);
        tip4ImageD = (ImageView) rootView.findViewById(R.id.italic_tip_4_d_image);
        tip4ImageE = (ImageView) rootView.findViewById(R.id.italic_tip_4_e_image);

        tip3Image.setOnClickListener(this);
        tip4ImageA.setOnClickListener(this);
        tip4ImageB.setOnClickListener(this);
        tip4ImageC.setOnClickListener(this);
        tip4ImageD.setOnClickListener(this);
        tip4ImageE.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.italic_tip_3_image:
                Intent intent1 = new Intent(getActivity(), DisplayActivity.class);
                intent1.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_f_640);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip3Image.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent1, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip3Image, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent1);
                }
                break;
            case R.id.italic_tip_4_a_image:
                Intent intent2 = new Intent(getActivity(), DisplayActivity.class);
                intent2.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_tip_4_a_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4ImageA.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip4ImageA, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent2);
                }
                break;
            case R.id.italic_tip_4_b_image:
                Intent intent3 = new Intent(getActivity(), DisplayActivity.class);
                intent3.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_tip_4_b_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4ImageB.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent3, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip4ImageB, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent3);
                }
                break;
            case R.id.italic_tip_4_c_image:
                Intent intent4 = new Intent(getActivity(), DisplayActivity.class);
                intent4.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_tip_4_c_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4ImageC.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent4, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip4ImageC, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent4);
                }
                break;
            case R.id.italic_tip_4_d_image:
                Intent intent5 = new Intent(getActivity(), DisplayActivity.class);
                intent5.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_tip_4_d_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4ImageD.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent5, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip4ImageD, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent5);
                }
                break;
            case R.id.italic_tip_4_e_image:
                Intent intent6 = new Intent(getActivity(), DisplayActivity.class);
                intent6.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_tip_4_e_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    tip4ImageE.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent6, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tip4ImageE, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent6);
                }
                break;
        }
    }
}
