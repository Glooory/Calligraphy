package com.glooory.calligraphy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.entity.PinsBean;
import com.glooory.calligraphy.net.ImageLoader;
import com.glooory.calligraphy.utils.Utils;


/**
 * Created by Glooory on 2016/11/5 0005 15:43.
 */

public class WorksRCAdapter extends BaseQuickAdapter<PinsBean> {
    private final int resizeWidth;
    private String mGeneralImgUrl;

    public WorksRCAdapter(Context context) {
        super(R.layout.card_works, null);
        mGeneralImgUrl = context.getString(R.string.format_url_image_general);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        resizeWidth = (int) ((displayMetrics.widthPixels - 24 * displayMetrics.density) * 0.5);//图片宽度为当前屏幕的一半
    }

    @Override
    protected void convert(BaseViewHolder holder, PinsBean pinsBean) {
        //图片地址
        String urlImg = String.format(mGeneralImgUrl, pinsBean.getFile().getKey());

        float mRatio = ((float) pinsBean.getFile().getWidth()) / ((float) pinsBean.getFile().getHeight());
        int resizeHeight = (int) (resizeWidth / mRatio);//等比计算图片的高度
        ViewGroup.LayoutParams params = holder.getView(R.id.card_works_img).getLayoutParams();
        params.width = resizeWidth;
        params.height = resizeHeight;
        holder.getView(R.id.card_works_img).setLayoutParams(params);

        //是否需要显示GIFImageButton
        if (Utils.checkIsGif(pinsBean.getFile().getType())) {
            holder.getView(R.id.imgbtn_gif).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.imgbtn_gif).setVisibility(View.INVISIBLE);
        }

        //加载pin图片
        ImageLoader.load(mContext, (ImageView) holder.getView(R.id.card_works_img), urlImg, getPlaceHolder(pinsBean, urlImg));
    }

    private Drawable getPlaceHolder(PinsBean bean, String url) {
        ColorDrawable drawable;
        if (bean.getFile().getColors() != null && bean.getFile().getColors().size() > 0) {
            int color = bean.getFile().getColors().get(0).getColor();
            if (color == 0) {
                //黑色
                drawable = new ColorDrawable(ContextCompat.getColor(mContext, R.color.grey_1000));
            } else {
                String hexColor = Integer.toHexString(color);
                if (hexColor.length() == 4) {
                    hexColor = "00" + hexColor;
                }
                if (hexColor.length() == 5) {
                    hexColor = "0" + hexColor;
                }
                drawable = new ColorDrawable(Color.parseColor("#" + hexColor));
            }
        } else {
            drawable = new ColorDrawable(ContextCompat.getColor(mContext, R.color.grey_a700));
        }
        return drawable;
    }

}
