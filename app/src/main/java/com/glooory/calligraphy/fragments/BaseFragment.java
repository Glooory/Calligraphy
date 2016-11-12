package com.glooory.calligraphy.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.glooory.calligraphy.activities.ImageDetailActivity;
import com.umeng.analytics.MobclickAgent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Glooory on 2016/7/19 0019.
 */
public class BaseFragment extends Fragment {
    private CompositeSubscription mCompositeSubscription;
    Context mContext;

    public void addSubscription(Subscription subscription) {
        if (subscription == null) {
            return;
        }

        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    public void startImgDetailActivity(ImageView imageView, int imgResId) {
        ImageDetailActivity.launch(getActivity(), imageView, imgResId);
    }

}
