package com.tourcoo.core.module.activity;

import android.os.Bundle;

import com.tourcoo.core.control.IFastMainView;
import com.tourcoo.core.delegate.MainTabDelegate;
import com.tourcoo.fusionmedia.R;


/**
 * Function: 快速创建主页Activity布局
 * Description:
 */
public abstract class BaseMainActivity extends BaseActivity implements IFastMainView {

    protected MainTabDelegate mFastMainTabDelegate;

    @Override
    public int getContentLayout() {
        return isSwipeEnable() ? R.layout.frame_activity_main_view_pager : R.layout.frame_activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
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
    public Bundle getSavedInstanceState() {
        return mSavedInstanceState;
    }

    @Override
    public void onBackPressed() {
        quitApp();
    }

    @Override
    protected void onDestroy() {
        if (mFastMainTabDelegate != null) {
            mFastMainTabDelegate.onDestroy();
        }
        super.onDestroy();
    }
}
