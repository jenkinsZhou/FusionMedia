package com.tourcoo.core.widget.view.tab.delegate;

import android.util.AttributeSet;
import android.view.View;

import com.tourcoo.core.widget.view.tab.listener.ITabLayout;
import com.tourcoo.fusionmedia.R;

/**
 * @Author: JenkinsZhou on 2018/12/3 9:19
 * @E-Mail: 971613168@qq.com
 * @Description:
 */
public class TabSlidingDelegate extends TabCommonSlidingDelegate<TabSlidingDelegate> {

    private boolean mIndicatorWidthEqualTitle;

    public TabSlidingDelegate(View view, AttributeSet attrs, ITabLayout iTabLayout) {
        super(view, attrs, iTabLayout);
        mTabSpaceEqual = mTypedArray.getBoolean(R.styleable.TabLayout_tl_tab_space_equal, false);

        mTabPadding = mTypedArray.getDimensionPixelSize(R.styleable.TabLayout_tl_tab_padding, mTabSpaceEqual || mTabWidth > 0 ? dp2px(0) : dp2px(20));
        mIndicatorWidthEqualTitle = mTypedArray.getBoolean(R.styleable.TabLayout_tl_indicator_width_equal_title, false);
    }

    public TabSlidingDelegate setIndicatorWidthEqualTitle(boolean indicatorWidthEqualTitle) {
        this.mIndicatorWidthEqualTitle = indicatorWidthEqualTitle;
        return back();
    }


    public boolean isIndicatorWidthEqualTitle() {
        return mIndicatorWidthEqualTitle;
    }
}
