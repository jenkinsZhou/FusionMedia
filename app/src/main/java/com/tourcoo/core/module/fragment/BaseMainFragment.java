package com.tourcoo.core.module.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.tourcoo.core.control.IFastMainView;
import com.tourcoo.core.delegate.MainTabDelegate;
import com.tourcoo.core.widget.view.tab.listener.OnTabSelectListener;
import com.tourcoo.fusionmedia.R;


/**
 * Function: 快速创建主页布局
 * Description:
 */
public abstract class BaseMainFragment extends BaseFragment implements IFastMainView, OnTabSelectListener {

    protected MainTabDelegate mFastMainTabDelegate;

    @Override
    public void setViewPager(ViewPager mViewPager) {
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

    @Override
    public int getContentLayout() {
        return isSwipeEnable() ? R.layout.frame_activity_main_view_pager : R.layout.frame_activity_main;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mFastMainTabDelegate != null) {
            mFastMainTabDelegate.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        mFastMainTabDelegate = new MainTabDelegate(mContentView, this, this);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public Bundle getSavedInstanceState() {
        return mSavedInstanceState;
    }
}
