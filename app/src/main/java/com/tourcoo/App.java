package com.tourcoo;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.simple.spiderman.SpiderMan;
import com.tourcoo.config.AppConfig;
import com.tourcoo.config.RequestConfig;
import com.tourcoo.core.UiManager;
import com.tourcoo.core.impl.ActivityControlImpl;
import com.tourcoo.core.impl.AppImpl;
import com.tourcoo.core.impl.HttpRequestControlImpl;
import com.tourcoo.core.log.LogLevel;
import com.tourcoo.core.log.TourCooLogUtil;
import com.tourcoo.core.retrofit.RetrofitHelper;
import com.tourcoo.core.utils.ToastUtil;

/**
 * @author :JenkinsZhou
 * @description :
 * @company :途酷科技
 * @date 2020/5/29 11:39
 * @Email: 971613168@qq.com
 */

public class App extends MultiDexApplication {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ToastUtil.init(this);
        TourCooLogUtil.getLogConfig()
                .configAllowLog(AppConfig.DEBUG_MODE)  // 是否在Logcat显示日志
                .configShowBorders(false) // 是否显示边框
                .configTagPrefix("FusionMedia") // 配置统一的TAG 前缀
//                .configFormatTag("%d{HH:mm:ss} %t %c{-5}") // 首行显示信息(可配置日期，线程等等)
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}") // 首行显示信息(可配置日期，线程等等)
                .configLevel(LogLevel.TYPE_VERBOSE); // 配置可展示日志等级
        TourCooLogUtil.getLog2FileConfig()
                //不开启日志写入文件
                .configLog2FileEnable(false);
        SpiderMan.init(application);
        // 调试模式下支持输入日志到文件
       /* if (AppConfig.DEBUG_MODE && PermissionUtil.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String filePath = Environment.getExternalStorageDirectory() + "/FusionMedia/logs/";
            TourCooLogUtil.getLog2FileConfig()
                    .configLog2FileEnable(true)
                    // 是否输出日志到文件
                    .configLogFileEngine(new LogFileEngineFactory(application))
                    // 日志文件引擎实现
                    .configLog2FilePath(filePath)
                    // 日志路径
                    .configLog2FileNameFormat("app-%d{yyyyMMdd}.txt")
                    // 日志文件名称
                    .configLog2FileLevel(LogLevel.TYPE_VERBOSE)
                    // 文件日志等级
                    .configLogFileFilter(new LogFileFilter() {
                        // 文件日志过滤
                        @Override
                        public boolean accept(int level, String tag, String logContent) {
                            return true;
                        }
                    });
        }*/
        //以下为更丰富自定义方法-可不设置即使用默认配置
        //全局UI配置参数-按需求设置
        AppImpl impl = new AppImpl(application);
        ActivityControlImpl activityControl = new ActivityControlImpl();
        UiManager.getInstance()
                //设置Adapter加载更多视图--默认设置了FastLoadMoreView
                .setLoadMoreFoot(impl)
                //全局设置RecyclerView
                .setFastRecyclerViewControl(impl)
                //设置RecyclerView加载过程多布局属性
                .setMultiStatusView(impl)
                //设置全局网络请求等待Loading提示框如登录等待loading--观察者必须为FastLoadingObserver及其子类
                .setLoadingDialog(impl)
                //设置SmartRefreshLayout刷新头-自定加载使用BaseRecyclerViewAdapterHelper
                .setDefaultRefreshHeader(impl)
                //设置全局TitleBarView相关配置
                .setTitleBarViewControl(impl)
                //设置Activity滑动返回控制-默认开启滑动返回功能不需要设置透明主题
//                .setSwipeBackControl(new SwipeBackControlImpl())
                //设置Activity/Fragment相关配置(横竖屏+背景+虚拟导航栏+状态栏+生命周期)
                .setActivityFragmentControl(activityControl)
                //设置BasisActivity 子类按键监听
                .setActivityKeyEventControl(activityControl)
                //配置BasisActivity 子类事件派发相关
                .setActivityDispatchEventControl(activityControl)
                //设置http请求结果全局控制
                .setHttpRequestControl(new HttpRequestControlImpl())
                //配置{@link FastObserver#onError(Throwable)}全局处理
                .setFastObserverControl(impl)
                //设置主页返回键控制-默认效果为2000 毫秒时延退出程序
                .setQuitAppControl(impl)
                //设置ToastUtil全局控制
                .setToastControl(impl);

        //初始化Retrofit配置
        RetrofitHelper.getInstance()
                //配置全局网络请求BaseUrl
                .setBaseUrl(RequestConfig.BASE_URL)
                //信任所有证书--也可设置setCertificates(单/双向验证)
                .setCertificates()
                //设置统一请求头
//                .addHeader(header)
//                .addHeader(key,value)
                //设置请求全局log-可设置tag及Level类型
                .setLogEnable(true)
//                .setLogEnable(BuildConfig.DEBUG, TAG, HttpLoggingInterceptor.Level.BODY)
                //设置统一超时--也可单独调用read/write/connect超时(可以设置时间单位TimeUnit)
                //默认20 s
                .setTimeout(30);

        //注意设置baseUrl要以/ 结尾 service 里的方法不要以/打头不然拦截到的url会有问题
        //以下为配置多BaseUrl--默认方式一优先级高 可通过FastRetrofit.getInstance().setHeaderPriorityEnable(true);设置方式二优先级
        //方式一 通过Service 里的method-(如:) 设置 推荐 使用该方式不需设置如方式二的额外Header
//        FastRetrofit.getInstance()
//                .putBaseUrl(ApiConstant.API_UPDATE_APP, BuildConfig.BASE__UPDATE_URL);

        //方式二 通过 Service 里添加特定header设置
        //step1
//        FastRetrofit.getInstance()
//                //设置Header模式优先-默认Method方式优先
//                .setHeaderPriorityEnable(true)
//                .putHeaderBaseUrl(ApiConstant.API_UPDATE_APP_KEY, BuildConfig.BASE__UPDATE_URL);
        //step2
        // 需要step1中baseUrl的方法需要在对应service里增加
        // @Headers({FastRetrofit.BASE_URL_NAME_HEADER + ApiConstant.API_UPDATE_APP_KEY})
        //增加一个Header配置注意FastRetrofit.BASE_URL_NAME_HEADER是必须为step1调用putHeaderBaseUrl方法设置的key
        // 参考com.aries.template.retrofit.service.ApiService#updateApp

        //其它初始化
    }


    public static Application getApplication(){
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
