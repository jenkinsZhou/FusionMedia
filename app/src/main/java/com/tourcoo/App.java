package com.tourcoo;

import android.app.Application;

import com.tourcoo.config.AppConfig;
import com.tourcoo.core.log.LogLevel;
import com.tourcoo.core.log.TourCooLogUtil;
import com.tourcoo.core.utils.ToastUtil;

/**
 * @author :JenkinsZhou
 * @description :
 * @company :途酷科技
 * @date 2020/5/29 11:39
 * @Email: 971613168@qq.com
 */

public class App extends Application {
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

    }


    public static Application getApplication(){
        return application;
    }
}
