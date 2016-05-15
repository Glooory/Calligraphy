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
 * Created by Glooo on 2016/5/10 0010.
 */
public class ItalicAdapter extends RecyclerView.Adapter<ItalicAdapter.ItalyItemHolder> {

    private int[] charImageIds;
    private LayoutInflater inflater;
    private int mPreviousPosition = 0;

    public ItalicAdapter(Context context) {
        initCharImageIds();
        inflater = LayoutInflater.from(context);
    }

    private void initCharImageIds() {
        charImageIds = new int[] {
                R.drawable.italy_a_640, R.drawable.italy_b_640, R.drawable.italy_c_640, R.drawable.italy_d_640,
                R.drawable.italy_e_640, R.drawable.italy_f_640, R.drawable.italy_g_640, R.drawable.italy_h_640,
                R.drawable.italy_i_640, R.drawable.italy_j_640, R.drawable.italy_k_640, R.drawable.italy_l_640,
                R.drawable.italy_m_640, R.drawable.italy_n_640, R.drawable.italy_o_640, R.drawable.italy_p_640,
                R.drawable.italy_q_640, R.drawable.italy_r_640, R.drawable.italy_s_640, R.drawable.italy_t_640,
                R.drawable.italy_u_640, R.drawable.italy_v_640, R.drawable.italy_w_640, R.drawable.italy_x_640,
                R.drawable.italy_y_640, R.drawable.italy_z_640,
        };
    }

    @Override
    public ItalyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_char, parent, false);
        ItalyItemHolder itemHolder = new ItalyItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItalyItemHolder holder, int position) {
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

    static class ItalyItemHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        public ItalyItemHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.card_char_image);
        }
    }


}
