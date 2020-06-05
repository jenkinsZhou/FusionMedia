package com.tourcoo.core.module.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kingja.loadsir.core.LoadService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tourcoo.core.control.IRefreshLoadView;
import com.tourcoo.core.control.IHttpRequestRefreshControl;
import com.tourcoo.core.delegate.RefreshLoadDelegate;


/**
 * Function:下拉刷新及上拉加载更多+多状态切换
 * Description:
 * 1、2018-7-20 16:55:45 设置StatusLayoutManager 目标View
 */
public abstract class BaseRefreshLoadFragment<T>
        extends BaseFragment implements IRefreshLoadView<T> {
    protected SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    //todo 更换
//    protected StatusLayoutManager mStatusManager;
    protected LoadService mLoadService;
    protected int mDefaultPage = 0;
    protected int mDefaultPageSize = 10;
    private BaseQuickAdapter mQuickAdapter;
    private Class<?> mClass;

    protected RefreshLoadDelegate<T> mFastRefreshLoadDelegate;

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        mClass = this.getClass();
        mFastRefreshLoadDelegate = new RefreshLoadDelegate<>(mContentView, this, mClass);
        mRecyclerView = mFastRefreshLoadDelegate.mRecyclerView;
        mRefreshLayout = mFastRefreshLoadDelegate.mRefreshLayout;
        //todo 更换
//        mStatusManager = mFastRefreshLoadDelegate.mStatusManager;
        mQuickAdapter = mFastRefreshLoadDelegate.mAdapter;
        mFastRefreshLoadDelegate.setLoadMore(isLoadMoreEnable());
    }

    @Override
    public IHttpRequestRefreshControl getIHttpRequestControl() {
        return new IHttpRequestRefreshControl() {
            @Override
            public SmartRefreshLayout getRefreshLayout() {
                return mRefreshLayout;
            }

            @Override
            public BaseQuickAdapter getRecyclerAdapter() {
                return mQuickAdapter;
            }

            @Override
            public LoadService getStatusLayoutManager() {
                return mLoadService;
            }

            @Override
            public int getCurrentPage() {
                return mDefaultPage;
            }

            @Override
            public int getPageSize() {
                return mDefaultPageSize;
            }

            @Override
            public Class<?> getRequestClass() {
                return mClass;
            }
        };
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mDefaultPage = 0;
        loadData(mDefaultPage);
    }

    @Override
    public void onLoadMoreRequested() {
        loadData(++mDefaultPage);
    }

    @Override
    public void loadData() {
        loadData(mDefaultPage);
    }

    @Override
    public void onDestroy() {
        if (mFastRefreshLoadDelegate != null) {
            mFastRefreshLoadDelegate.onDestroy();
        }
        super.onDestroy();
    }
}
