package com.tourcoo.core.module.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.tourcoo.core.control.ITitleView;
import com.tourcoo.core.utils.FindViewUtil;
import com.tourcoo.core.widget.view.title.TitleBarView;

/**
 * Function: 设置有TitleBar的Fragment
 * Description:
 * 1、2019-3-25 17:03:43 推荐使用{@link ITitleView}通过接口方式由FastLib自动处理{@link FrameLifecycleCallbacks#onFragmentStarted(FragmentManager, Fragment)}
 */
public abstract class BaseTitleFragment extends BaseFragment implements ITitleView {

    protected TitleBarView mTitleBar;

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        mTitleBar = FindViewUtil.getTargetView(mContentView, TitleBarView.class);
    }
}
