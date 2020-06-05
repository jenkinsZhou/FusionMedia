package com.tourcoo.core.control;


import com.tourcoo.core.retrofit.BaseObserver;

/**
 * @Function: {@link BaseObserver}错误信息拦截并做其它操作处理配合{@link  com.tourcoo.core.retrofit.FrameNullException}以处理解决接口某些情况下无法回调成功问题
 * @Description:
 */
public interface FrameObserverControl {

    /**
     * @param o {@link BaseObserver} 对象用于后续事件逻辑
     * @param e 原始错误
     * @return true 拦截操作不进行原始{@link BaseObserver#onError(Throwable)}后续逻辑
     * false 不拦截继续后续逻辑
     */
    boolean onError(BaseObserver o, Throwable e);
}
