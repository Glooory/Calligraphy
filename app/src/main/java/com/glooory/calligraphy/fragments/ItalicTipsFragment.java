package com.glooory.calligraphy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;

/**
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicTipsFragment extends BaseTipsFragment implements View.OnClickListener {
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
                startImgActivity(tip3Image, R.drawable.italy_f_640);
                break;
            case R.id.italic_tip_4_a_image:
                startImgActivity(tip4ImageA, R.drawable.italy_tip_4_a_480);
                break;
            case R.id.italic_tip_4_b_image:
                startImgActivity(tip4ImageB, R.drawable.italy_tip_4_b_480);
                break;
            case R.id.italic_tip_4_c_image:
                startImgActivity(tip4ImageC, R.drawable.italy_tip_4_c_480);
                break;
            case R.id.italic_tip_4_d_image:
                startImgActivity(tip4ImageD, R.drawable.italy_tip_4_d_480);
                break;
            case R.id.italic_tip_4_e_image:
                startImgActivity(tip4ImageE, R.drawable.italy_tip_4_e_480);
                break;
        }
    }
}
