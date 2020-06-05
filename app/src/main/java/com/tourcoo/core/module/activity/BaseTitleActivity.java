package com.tourcoo.core.module.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tourcoo.core.control.ITitleView;
import com.tourcoo.core.utils.FindViewUtil;
import com.tourcoo.core.widget.view.title.TitleBarView;


/**
 * Function: 包含TitleBarView的Activity
 * Description:
 * 1、2019-3-25 17:03:43 推荐使用{@link ITitleView}通过接口方式由FastLib自动处理{@link FastLifecycleCallbacks#onActivityStarted(Activity)}
 */
public abstract class BaseTitleActivity extends BaseActivity implements ITitleView {

    protected TitleBarView mTitleBar;

    @Override
    public void beforeInitView(Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        mTitleBar = FindViewUtil.getTargetView(mContentView, TitleBarView.class);
    }

    @Override
    protected void onDestroy() {
        mTitleBar = null;
        super.onDestroy();
    }
}
