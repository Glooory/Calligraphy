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
public class ItalicDesFragment extends Fragment {
    private CardView desImageCard;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.italic_des, container, false);
        desImageCard = (CardView) rootView.findViewById(R.id.italic_des_image_card);

        desImageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra(Constants.IMAGE_RES_ID, R.drawable.italy_header_480);
                if (Build.VERSION.SDK_INT >= 21) {
                    desImageCard.setTransitionName(getString(R.string.transition_name));
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), desImageCard, getString(R.string.transition_name)).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
}
