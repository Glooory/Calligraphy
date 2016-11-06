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
                startImgActivity(cardOP, R.drawable.op_640);
                break;
            case R.id.card_spen:
                startImgActivity(cardSpen, R.drawable.spen_orignal);
                break;
            case R.id.card_bp:
                startImgActivity(cardBP, R.drawable.bp_640);
                break;
            case R.id.card_gothic:
                startImgActivity(cardGothic, R.drawable.gothic_480);
                break;
        }
    }

    private void startImgActivity(CardView cardView, int imgResId) {
        Intent intent = new Intent(getActivity(), DisplayActivity.class);
        intent.putExtra(Constants.IMAGE_RES_ID, imgResId);
        if (Build.VERSION.SDK_INT >= 21) {
            cardView.setTransitionName(getString(R.string.transition_name));
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(), cardView, getString(R.string.transition_name)).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
