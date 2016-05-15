package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glooory.calligraphy.R;
import com.glooory.calligraphy.anim.AnimationUtils;

/**
 * Created by Glooo on 2016/5/9 0009.
 */
public class RoundhandAdapter extends RecyclerView.Adapter<RoundhandAdapter.RoundhandItemHolder> {

    private int[] charImageIds;
    private LayoutInflater inflater;
    private int mPreviousPosition = 0;

    public RoundhandAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        initCharImageIds();
    }

    private void initCharImageIds() {
        charImageIds = new int[]{R.drawable.roundhand_a_640, R.drawable.roundhand_b_640, R.drawable.roundhand_c_640, R.drawable.roundhand_d_640,
                R.drawable.roundhand_e_640, R.drawable.roundhand_f_640, R.drawable.roundhand_g_640, R.drawable.roundhand_h_640,
                R.drawable.roundhand_i_640, R.drawable.roundhand_j_640, R.drawable.roundhand_k_640, R.drawable.roundhand_l_640,
                R.drawable.roundhand_m_640, R.drawable.roundhand_n_640, R.drawable.roundhand_o_640, R.drawable.roundhand_p_640,
                R.drawable.roundhand_q_640, R.drawable.roundhand_r_640, R.drawable.roundhand_s_640, R.drawable.roundhand_t_640,
                R.drawable.roundhand_u_640, R.drawable.roundhand_v_640, R.drawable.roundhand_w_640, R.drawable.roundhand_x_640,
                R.drawable.roundhand_y_640, R.drawable.roundhand_z_640};
    }

    @Override
    public RoundhandItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_char, parent, false);
        RoundhandItemHolder holder = new RoundhandItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RoundhandItemHolder holder, int position) {
        holder.charImage.setImageResource(charImageIds[position]);

        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
        } else {
            AnimationUtils.animateSunblind(holder, false);
        }

        mPreviousPosition = position;
    }


    @Override
    public int getItemCount() {
        return charImageIds.length;
    }

    static class RoundhandItemHolder extends RecyclerView.ViewHolder {
        ImageView charImage;

        public RoundhandItemHolder(View itemView) {
            super(itemView);
            charImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }

}
