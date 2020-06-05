package com.tourcoo.core.control;

import java.util.List;

/**
 * Function: http请求成功后处理结果回调{@link HttpRequestControl#httpRequestSuccess(IHttpRequestRefreshControl, List, OnHttpRequestListener)}
 * Description:
 */
public interface OnHttpRequestListener {

    /**
     * 无数据回调
     */
    void onEmpty();

    /**
     * 无更多数据回调
     */
    void onNoMore();

    /**
     * 加载数据回调
     */
    void onNext();
}
