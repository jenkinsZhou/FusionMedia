package com.tourcoo.core.entity;

import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.tourcoo.core.UiManager;
import com.tourcoo.core.widget.view.tab.listener.CustomTabEntity;

/**
 * Function: 主页Tab实体类
 * Description:
 * 1、2018-7-27 17:45:45 修改重载方式
 */
public class FrameTabEntity implements CustomTabEntity {
    public String mTitle;
    public int mSelectedIcon;
    public int mUnSelectedIcon;
    public Fragment mFragment;

    public FrameTabEntity(String title, int unSelectedIcon, int selectedIcon, Fragment fragment) {
        this.mTitle = title;
        this.mSelectedIcon = selectedIcon;
        this.mUnSelectedIcon = unSelectedIcon;
        this.mFragment = fragment;
    }

    public FrameTabEntity(int title, int unSelectedIcon, int selectedIcon, Fragment fragment) {
        this(UiManager.getInstance().getApplication().getString(title), unSelectedIcon, selectedIcon, fragment);
    }

    public FrameTabEntity(int unSelectedIcon, int selectedIcon, Fragment fragment) {
        this("", unSelectedIcon, selectedIcon, fragment);
    }

    @Override
    public String getTabTitle() {
        if (TextUtils.isEmpty(mTitle)) {
            return "";
        }
        return mTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return mSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return mUnSelectedIcon;
    }
}
