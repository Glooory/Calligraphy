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

import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.activities.DisplayActivity;

/**
 * Created by Glooo on 2016/5/13 0013.
 */
public class OtherfontsFragment extends Fragment implements View.OnClickListener {
    private CardView cardOP;
    private CardView cardSpen;
    private CardView cardBP;
    private CardView cardGothic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.other_fonts, container, false);

        cardOP = (CardView) rootView.findViewById(R.id.card_op);
        cardSpen = (CardView) rootView.findViewById(R.id.card_spen);
        cardBP = (CardView) rootView.findViewById(R.id.card_bp);
        cardGothic = (CardView) rootView.findViewById(R.id.card_gothic);
        cardOP.setOnClickListener(this);
        cardSpen.setOnClickListener(this);
        cardBP.setOnClickListener(this);
        cardGothic.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_op:
                Intent intent1 = new Intent(getActivity(), DisplayActivity.class);
                intent1.putExtra(Constants.IMAGE_RES_ID, R.drawable.op_640);
                if (Build.VERSION.SDK_INT >= 21) {
                    cardOP.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent1, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), cardOP, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent1);
                }
                break;
            case R.id.card_spen:
                Intent intent2 = new Intent(getActivity(), DisplayActivity.class);
                intent2.putExtra(Constants.IMAGE_RES_ID, R.drawable.spen_orignal);
                if (Build.VERSION.SDK_INT >= 21) {
                    cardSpen.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), cardSpen, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent2);
                }
                break;
            case R.id.card_bp:
                Intent intent3 = new Intent(getActivity(), DisplayActivity.class);
                intent3.putExtra(Constants.IMAGE_RES_ID, R.drawable.bp_640);
                if (Build.VERSION.SDK_INT >= 21) {
                    cardBP.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent3, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), cardBP, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent3);
                }
                break;
            case R.id.card_gothic:
                Intent intent4 = new Intent(getActivity(), DisplayActivity.class);
                intent4.putExtra(Constants.IMAGE_RES_ID, R.drawable.gothic_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    cardGothic.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent4, ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(), cardGothic, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent4);
                }
                break;
        }
    }
}
