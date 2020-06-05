package com.tourcoo.core.control;


import com.tourcoo.core.widget.view.title.TitleBarView;

/**
 * Function: 全局TitleBarView属性控制
 * Description:
 */
public interface TitleBarViewControl {

    /**
     * 全局设置TitleBarView 属性回调
     *
     * @param titleBar
     * @param cls 包含TitleBarView的类
     * @return
     */
    boolean createTitleBarViewControl(TitleBarView titleBar, Class<?> cls);
}
