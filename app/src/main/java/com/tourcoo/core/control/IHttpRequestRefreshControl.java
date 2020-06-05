package com.tourcoo.core.control;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kingja.loadsir.core.LoadService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * Function: 基于实现{@link IRefreshLoadView}下拉刷新、列表、多状态布局的全局回调接口
 * Description:
 */
public interface IHttpRequestRefreshControl {

    /**
     * 获取刷新布局
     *
     * @return
     */
    SmartRefreshLayout getRefreshLayout();

    /**
     * 获取RecyclerView Adapter
     *
     * @return
     */
    BaseQuickAdapter getRecyclerAdapter();

    /**
     * 获取多布局状态管理
     *
     * @return
     */
//    StatusLayoutManager getStatusLayoutManager();

    LoadService getStatusLayoutManager();

    /**
     * 获取当前页码
     * @return
     */
    int getCurrentPage();

    /**
     * 获取每页数量
     * @return
     */
    int getPageSize();

    /**
     * 获取调用类
     * @return
     */
    Class<?> getRequestClass();

}
