package com.tourcoo.core.widget.view.tab.listener;

import androidx.annotation.DrawableRes;

/**
 * @Author: JenkinsZhou on 2018/12/3 13:07
 * @E-Mail: 971613168@qq.com
 * @Description:
 */
public interface CustomTabEntity {
    /**
     * tab文本
     *
     * @return
     */
    String getTabTitle();

    /**
     * tab 选中icon 资源id
     *
     * @return
     */
    @DrawableRes
    int getTabSelectedIcon();

    /**
     * tab未选中icon资源
     *
     * @return
     */
    @DrawableRes
    int getTabUnselectedIcon();
}