package com.tourcoo.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.tourcoo.App;
import com.tourcoo.core.control.ActivityDispatchEventControl;
import com.tourcoo.core.control.ActivityFragmentControl;
import com.tourcoo.core.control.ActivityKeyEventControl;
import com.tourcoo.core.control.FastRecyclerViewControl;
import com.tourcoo.core.control.FrameObserverControl;
import com.tourcoo.core.control.HttpRequestControl;
import com.tourcoo.core.control.LoadMoreFoot;
import com.tourcoo.core.control.LoadingDialog;
import com.tourcoo.core.control.MultiStatusView;
import com.tourcoo.core.control.QuitAppControl;
import com.tourcoo.core.control.SwipeBackControl;
import com.tourcoo.core.control.TitleBarViewControl;
import com.tourcoo.core.control.ToastControl;
import com.tourcoo.core.lifecycle.FrameLifecycleCallbacks;
import com.tourcoo.core.manager.GlideManager;
import com.tourcoo.core.utils.FrameUtil;
import com.tourcoo.core.utils.ToastUtil;
import com.tourcoo.core.widget.dialog.LoadingDialogWrapper;
import com.tourcoo.core.widget.dialog.loading.IosLoadingDialog;
import com.tourcoo.fusionmedia.R;

import static com.tourcoo.core.FrameConstant.EXCEPTION_NOT_INIT_FAST_MANAGER;


/**
 * Function: 各种UI相关配置属性
 * Description:
 * 1、2018-9-26 16:58:14 新增BasisActivity 子类前台监听按键事件
 */
public class UiManager {

    //原本在Provider中默认进行初始化,如果app出现多进程使用该模式可避免调用异常出现
    static {
        Application application = App.getApplication();
        if (application != null) {
            Log.i("FastManager", "initSuccess");
            init(application);
        }
    }

    private static volatile UiManager sInstance;

    private UiManager() {
    }

    public static UiManager getInstance() {
        if (sInstance == null) {
            throw new NullPointerException(EXCEPTION_NOT_INIT_FAST_MANAGER);
        }
        return sInstance;
    }

    private static Application mApplication;
    /**
     * Adapter加载更多View
     */
    private LoadMoreFoot mLoadMoreFoot;
    /**
     * 全局设置列表
     */
    private FastRecyclerViewControl mFastRecyclerViewControl;
    /**
     * SmartRefreshLayout默认刷新头
     */
    private DefaultRefreshHeaderCreator mDefaultRefreshHeader;
    /**
     * 多状态布局--加载中/空数据/错误/无网络
     */
    private MultiStatusView mMultiStatusView;
    /**
     * 配置全局通用加载等待Loading提示框
     */
    private LoadingDialog mLoadingDialog;
    /**
     * 配置TitleBarView相关属性
     */
    private TitleBarViewControl mTitleBarViewControl;
    /**
     * 配置Activity滑动返回相关属性
     */
    private SwipeBackControl mSwipeBackControl;
    /**
     * 配置Activity/Fragment(背景+Activity强制横竖屏+Activity 生命周期回调+Fragment生命周期回调)
     */
    private ActivityFragmentControl mActivityFragmentControl;

    /**
     * 配置BasisActivity 子类前台时监听按键相关
     */
    private ActivityKeyEventControl mActivityKeyEventControl;

    /**
     * 配置BasisActivity 子类事件派发相关
     */
    private ActivityDispatchEventControl mActivityDispatchEventControl;
    /**
     * 配置网络请求
     */
    private HttpRequestControl mHttpRequestControl;

    /**
     * 配置{@link com.tourcoo.core.retrofit.BaseObserver#onError(Throwable)}全局处理
     */
    private FrameObserverControl mFastObserverControl;
    /**
     * Activity 主页点击返回键控制
     */
    private QuitAppControl mQuitAppControl;
    /**
     * ToastUtil相关配置
     */
    private ToastControl mToastControl;

    public Application getApplication() {
        return mApplication;
    }

    /**
     * 滑动返回基础配置查看{@link FrameLifecycleCallbacks#onActivityCreated(Activity, Bundle)}
     * 不允许外部调用
     *
     * @param application Application 对象
     * @return
     */
    static UiManager init(Application application) {
        Log.i("FastManager","init_mApplication:" + mApplication + ";application;" + application);
        //保证只执行一次初始化属性
        if (mApplication == null && application != null) {
            mApplication = application;
            sInstance = new UiManager();
            //预设置FastLoadDialog属性
            sInstance.setLoadingDialog(new LoadingDialog() {
                @Nullable
                @Override
                public LoadingDialogWrapper createLoadingDialog(@Nullable Activity activity) {
                    return new LoadingDialogWrapper(activity, new IosLoadingDialog(activity,"请稍后..."));
                }
            });
            //设置检测滑动返回是否导入
            if (FrameUtil.isClassExist(FrameConstant.BGA_SWIPE_BACK_HELPER_CLASS)) {
                //设置滑动返回监听 todo 这里暂时屏蔽
//                BGASwipeBackHelper.init(mApplication, null);
            }
            //注册activity生命周期
            mApplication.registerActivityLifecycleCallbacks(new FrameLifecycleCallbacks());
            //初始化Toast工具
            ToastUtil.init(mApplication);
            //初始化Glide
            GlideManager.setPlaceholderColor(ContextCompat.getColor(mApplication, R.color.colorPlaceholder));
            GlideManager.setPlaceholderRoundRadius(mApplication.getResources().getDimension(R.dimen.dp_placeholder_radius));
        }
        return getInstance();
    }


    public LoadMoreFoot getLoadMoreFoot() {
        return mLoadMoreFoot;
    }

    /**
     * 设置Adapter统一加载更多相关脚布局
     * 最终调用{@link FastRefreshLoadDelegate#initRecyclerView()}
     *
     * @param mLoadMoreFoot
     * @return
     */
    public UiManager setLoadMoreFoot(LoadMoreFoot mLoadMoreFoot) {
        this.mLoadMoreFoot = mLoadMoreFoot;
        return this;
    }

    public FastRecyclerViewControl getFastRecyclerViewControl() {
        return mFastRecyclerViewControl;
    }

    /**
     * 全局设置列表
     *
     * @param control
     * @return
     */
    public UiManager setFastRecyclerViewControl(FastRecyclerViewControl control) {
        this.mFastRecyclerViewControl = control;
        return this;
    }

    public DefaultRefreshHeaderCreator getDefaultRefreshHeader() {
        return mDefaultRefreshHeader;
    }

    /**
     * 设置SmartRefreshLayout 下拉刷新头
     * 最终调用{@link FastRefreshDelegate#initRefreshHeader()}
     *
     * @param control
     * @return
     */
    public UiManager setDefaultRefreshHeader(DefaultRefreshHeaderCreator control) {
        this.mDefaultRefreshHeader = control;
        return sInstance;
    }

    public MultiStatusView getMultiStatusView() {
        return mMultiStatusView;
    }

    /**
     * 设置多状态布局--加载中/空数据/错误/无网络
     * 最终调用{@link FastRefreshDelegate#initRefreshHeader()}
     *
     * @param control
     * @return
     */
    public UiManager setMultiStatusView(MultiStatusView control) {
        this.mMultiStatusView = control;
        return this;
    }

    public LoadingDialog getLoadingDialog() {
        return mLoadingDialog;
    }

    /**
     * 设置全局网络请求等待Loading提示框如登录等待loading
     * 最终调用{@link FastLoadingObserver#FastLoadingObserver(Activity)}
     *
     * @param control
     * @return
     */
    public UiManager setLoadingDialog(LoadingDialog control) {
        if (control != null) {
            this.mLoadingDialog = control;
        }
        return this;
    }

    public TitleBarViewControl getTitleBarViewControl() {
        return mTitleBarViewControl;
    }

    public UiManager setTitleBarViewControl(TitleBarViewControl control) {
        mTitleBarViewControl = control;
        return this;
    }

    @Deprecated
    public SwipeBackControl getSwipeBackControl() {
        return mSwipeBackControl;
    }

    /**
     * 配置Activity滑动返回相关属性
     *
     * @param control
     * @return
     */
    @Deprecated
    public UiManager setSwipeBackControl(SwipeBackControl control) {
        mSwipeBackControl = control;
        return this;
    }

    public ActivityFragmentControl getActivityFragmentControl() {
        return mActivityFragmentControl;
    }

    /**
     * 配置Activity/Fragment(背景+Activity强制横竖屏+Activity 生命周期回调+Fragment生命周期回调)
     *
     * @param control
     * @return
     */
    public UiManager setActivityFragmentControl(ActivityFragmentControl control) {
        mActivityFragmentControl = control;
        return this;
    }

    public ActivityKeyEventControl getActivityKeyEventControl() {
        return mActivityKeyEventControl;
    }

    /**
     * 配置BasisActivity 子类前台时监听按键相关
     *
     * @param control
     * @return
     */
    public UiManager setActivityKeyEventControl(ActivityKeyEventControl control) {
        mActivityKeyEventControl = control;
        return this;
    }

    public ActivityDispatchEventControl getActivityDispatchEventControl() {
        return mActivityDispatchEventControl;
    }

    /**
     * 配置BasisActivity 子类事件派发相关
     *
     * @param control
     * @return
     */
    public UiManager setActivityDispatchEventControl(ActivityDispatchEventControl control) {
        mActivityDispatchEventControl = control;
        return this;
    }

    public HttpRequestControl getHttpRequestControl() {
        return mHttpRequestControl;
    }

    /**
     * 配置Http请求成功及失败相关回调-方便全局处理
     *
     * @param control
     * @return
     */
    public UiManager setHttpRequestControl(HttpRequestControl control) {
        mHttpRequestControl = control;
        return this;
    }

    public FrameObserverControl getFastObserverControl() {
        return mFastObserverControl;
    }

    /**
     * 配置{@link BaseObserver#onError(Throwable)}全局处理
     *
     * @param control FastObserverControl对象
     * @return
     */
    public UiManager setFastObserverControl(FrameObserverControl control) {
        mFastObserverControl = control;
        return this;
    }

    public QuitAppControl getQuitAppControl() {
        return mQuitAppControl;
    }

    /**
     * 配置Http请求成功及失败相关回调-方便全局处理
     *
     * @param control
     * @return
     */
    public UiManager setQuitAppControl(QuitAppControl control) {
        mQuitAppControl = control;
        return this;
    }

    public ToastControl getToastControl() {
        return mToastControl;
    }

    /**
     * 配置ToastUtil
     *
     * @param control
     * @return
     */
    public UiManager setToastControl(ToastControl control) {
        mToastControl = control;
        return this;
    }
}
