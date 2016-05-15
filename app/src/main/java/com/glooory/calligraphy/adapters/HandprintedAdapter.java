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
 * Created by Glooo on 2016/5/11 0011.
 */
public class HandprintedAdapter extends RecyclerView.Adapter<HandprintedAdapter.ItemHolder> {

    private int[] charImageIds;
    private LayoutInflater inflater;
    private int mPreviousPosition = 0;

    public HandprintedAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        initCharImageIds();
    }

    private void initCharImageIds() {
        charImageIds = new int[] {
                R.drawable.handprinted_a_640, R.drawable.handprinted_b_640, R.drawable.handprinted_c_640, R.drawable.handprinted_d_640,
                R.drawable.handprinted_e_640, R.drawable.handprinted_f_640, R.drawable.handprinted_g_640, R.drawable.handprinted_h_640,
                R.drawable.handprinted_i_640, R.drawable.handprinted_j_640, R.drawable.handprinted_k_640, R.drawable.handprinted_l_640,
                R.drawable.handprinted_m_640, R.drawable.handprinted_n_640, R.drawable.handprinted_o_640, R.drawable.handprinted_p_640,
                R.drawable.handprinted_q_640, R.drawable.handprinted_r_640, R.drawable.handprinted_s_640, R.drawable.handprinted_t_640,
                R.drawable.handprinted_u_640, R.drawable.handprinted_v_640, R.drawable.handprinted_w_640, R.drawable.handprinted_x_640,
                R.drawable.handprinted_y_640, R.drawable.handprinted_z_640
        };
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_char, parent, false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.itemImage.setImageResource(charImageIds[position]);

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


    static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;

        public ItemHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }

}
