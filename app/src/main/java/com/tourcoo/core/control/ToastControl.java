package com.tourcoo.core.control;

import android.widget.Toast;

import com.tourcoo.core.widget.view.radius.RadiusTextView;


/**
 * @Description:
 */
public interface ToastControl {

    /**
     * 处理其它异常情况
     *
     * @return
     */
    Toast getToast();

    /**
     * 设置Toast
     *
     * @param toast    ToastUtil 中的Toast
     * @param textView ToastUtil 中的Toast设置的View
     */
    void setToast(Toast toast, RadiusTextView textView);
}
