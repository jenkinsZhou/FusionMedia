package com.tourcoo.core.control;

import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;


/**
 * Function: StatusLayoutManager 属性控制
 * Description:
 */
public interface IMultiStatusView extends Callback.OnReloadListener {
    /**
     * 设置StatusLayoutManager 的目标View
     *
     * @return
     */
    default View getMultiStatusContentView() {
        return null;
    }

   /* *//**
     * 设置StatusLayoutManager属性
     *
     * @param loadSir
     *//*
    default void setMultiStatusView(LoadSir loadSir) {

    }*/

    /**
     * 设置StatusLayoutManager
     *
     * @param manager
     */
    default void setMultiStatusViewManager(LoadService manager) {
    }

    /**
     * 获取空布局里点击View回调
     *
     * @return
     */
    default View.OnClickListener getEmptyClickListener() {
        return null;
    }

    /**
     * 获取错误布局里点击View回调
     *
     * @return
     */
    default View.OnClickListener getErrorClickListener() {
        return null;
    }

    /**
     * 获取自定义布局里点击View回调
     *
     * @return
     */
    default View.OnClickListener getCustomerClickListener() {
        return null;
    }

}
