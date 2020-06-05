package com.tourcoo.core.control;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Function: {@link IRefreshLoadView}列表布局全局控制RecyclerView
 * @Description:
 */
public interface FastRecyclerViewControl {

    /**
     * 全局设置
     *
     * @param recyclerView
     * @param cls
     */
    void setRecyclerView(RecyclerView recyclerView, Class<?> cls);
}
