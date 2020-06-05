package com.tourcoo.core.control;


import com.kingja.loadsir.core.LoadService;

/**
 * Function: 用于全局设置多状态布局
 * Description:
 * 1、修改设置多状态布局方式
 */
public interface MultiStatusView {

    /**
     * 设置多状态布局属性
     *
     * @param loadService
     * @param iFastRefreshLoadView
     */
//    void setMultiStatusView(StatusLayoutManager.Builder statusView, IFastRefreshLoadView iFastRefreshLoadView);


    void setMultiStatusView(LoadService loadService, IRefreshLoadView iFastRefreshLoadView);
}
