package com.tourcoo.core.module.fragment;

import android.os.Bundle;

import com.tourcoo.core.control.ITitleView;
import com.tourcoo.core.delegate.TitleDelegate;
import com.tourcoo.core.widget.view.title.TitleBarView;


/**
 * Function: 设置有TitleBar及下拉刷新Fragment
 * Description:
 */
public abstract class BaseTitleRefreshLoadFragment<T> extends BaseRefreshLoadFragment<T> implements ITitleView {

    protected TitleBarView mTitleBar;

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
        mTitleBar = new TitleDelegate(mContentView, this, getClass()).mTitleBar;
        super.beforeInitView(savedInstanceState);
    }
}
