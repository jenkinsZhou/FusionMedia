package com.tourcoo.core.control;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.tourcoo.core.widget.dialog.LoadingDialogWrapper;

/**
 * Function: 用于全局配置网络请求登录Loading提示框
 * Description:
 */
public interface LoadingDialog {

    /**
     * 设置快速Loading Dialog
     *
     * @param activity
     * @return
     */
    @Nullable
    LoadingDialogWrapper createLoadingDialog(@Nullable Activity activity);
}
