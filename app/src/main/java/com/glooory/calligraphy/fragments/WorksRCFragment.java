package com.glooory.calligraphy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.glooory.calligraphy.R;
import com.glooory.calligraphy.adapters.WorksRCAdapter;
import com.glooory.calligraphy.api.PinsApi;
import com.glooory.calligraphy.api.RetrofitClient;
import com.glooory.calligraphy.callbacks.FragmentRefreshListener;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.entity.PinsBean;
import com.glooory.calligraphy.entity.PinsListBean;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Glooory on 2016/11/5 0005 17:20.
 */

public class WorksRCFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener {
    private CompositeSubscription mCompositeSubscription;
    private String mBoardId;
    private final int PAGESIZE = 20;
    private int mDataCountLastRequested = 20;
    private String mMaxId;
    private WorksRCAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private View mFooterView;
    private Context mContext;
    private FragmentRefreshListener mListener;

    public static WorksRCFragment newInstanse(int worksTypeIndex) {
        Bundle args = new Bundle();
        args.putInt(Constants.WORKS_INDEX, worksTypeIndex);
        WorksRCFragment fragment = new WorksRCFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mListener = (FragmentRefreshListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int worksTypeIndex = getArguments().getInt(Constants.WORKS_INDEX, Constants.WORKS_NORMAL_INDEX);
        if (worksTypeIndex == Constants.WORKS_NORMAL_INDEX) {
            mBoardId = Constants.CALLI_BOARD_ID;
        } else {
            mBoardId = Constants.FLOURISHING_BOARD_ID;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mFooterView = inflater.inflate(R.layout.view_no_more_data_footer, null);
        httpForFirstTime();
        return mRecyclerView;
    }

    private void initAdapter() {
        mAdapter = new WorksRCAdapter(mContext);
        //设置上滑自动建在的正在加载更多的自定义View
        View loadMoreView = LayoutInflater.from(mContext).inflate(R.layout.custom_loadmore_view, mRecyclerView, false);
        mAdapter.setLoadingView(loadMoreView);

        //当当前position等于PAGE_SIZE 时，就回调用onLoadMoreRequested() 自动加载下一页数据
        mAdapter.openLoadMore(PAGESIZE);
        mAdapter.openLoadAnimation();
        mAdapter.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                // TODO: 2016/11/5 0005 launch image detail activity
            }
        });
    }

    private void httpForFirstTime() {
        if (mListener != null) {
            mListener.requestRefresh();
        }
        Subscription s = RetrofitClient.createService(PinsApi.class)
                .getPinsList(mBoardId, 20)
                .map(new Func1<PinsListBean, List<PinsBean>>() {
                    @Override
                    public List<PinsBean> call(PinsListBean pinsListBean) {
                        return pinsListBean.getPins();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PinsBean>>() {
                    @Override
                    public void onCompleted() {
                        if (mListener != null) {
                            mListener.requestRefreshDone();
                        }
                        checkIfAddFooter();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (mListener != null) {
                            mListener.requestRefreshDone();
                        }
                        Toast.makeText(mContext, "加载出错！", Toast.LENGTH_LONG).show();
                        checkIfAddFooter();
                    }

                    @Override
                    public void onNext(List<PinsBean> pinsBeen) {
                        mAdapter.setNewData(pinsBeen);
                        saveMaxId(pinsBeen);
                    }
                });
        addSubscription(s);
    }

    private void httpForMore() {
        Subscription s = RetrofitClient.createService(PinsApi.class)
                .getPinsListMax(mBoardId, mMaxId, 20)
                .map(new Func1<PinsListBean, List<PinsBean>>() {
                    @Override
                    public List<PinsBean> call(PinsListBean pinsListBean) {
                        return pinsListBean.getPins();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PinsBean>>() {
                    @Override
                    public void onCompleted() {
                        checkIfAddFooter();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "加载出错！", Toast.LENGTH_LONG).show();
                        checkIfAddFooter();
                    }

                    @Override
                    public void onNext(List<PinsBean> pinsBeen) {
                        mAdapter.addData(pinsBeen);
                        saveMaxId(pinsBeen);
                    }
                });
        addSubscription(s);
    }

    private void saveMaxId(List<PinsBean> list) {
        if (list != null) {
            if (list.size() > 0) {
                mMaxId = list.get(list.size() - 1).getPin_id();
                mDataCountLastRequested = list.size();
            }
        }
    }

    public void addSubscription(Subscription subscription) {
        if (subscription == null) {
            return;
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    /**
     * 判断当前数据是否已经加载完毕和加没有更多了的FooterView
     */
    public void checkIfAddFooter() {
        if (mDataCountLastRequested < PAGESIZE) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    setAdapterLoadComplete();
                }
            });
        }
    }

    /**
     * 数据加载完毕，adapter加上footerview
     */
    public void setAdapterLoadComplete() {
        if (mFooterView.getParent() != null) {
            ((ViewGroup) mFooterView.getParent()).removeView(mFooterView);
        }
        mAdapter.loadComplete();
        mAdapter.addFooterView(mFooterView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mDataCountLastRequested < PAGESIZE) {
                    setAdapterLoadComplete();
                } else {
                    httpForMore();
                }
            }
        });
    }

    /**
     * 刷新当前子类的数据，提供给Activity调用
     */
    public void refreshData(){
        httpForFirstTime();
    }
}