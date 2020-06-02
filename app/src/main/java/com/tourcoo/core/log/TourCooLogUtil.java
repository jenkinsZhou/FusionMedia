package com.tourcoo.core.log;


import com.tourcoo.core.config.Log2FileConfig;
import com.tourcoo.core.config.Log2FileConfigImpl;
import com.tourcoo.core.config.LogConfig;
import com.tourcoo.core.config.LogConfigImpl;

/**
 * Created by pengwei08 on 2015/7/16.
 * 日志管理器
 */
public final class TourCooLogUtil {

    private static Logger printer = new Logger();
    private static LogConfigImpl logConfig = LogConfigImpl.getInstance();
    private static Log2FileConfigImpl log2FileConfig = Log2FileConfigImpl.getInstance();

    /**
     * 选项配置
     *
     * @return LogConfig
     */
    public static LogConfig getLogConfig() {
        return logConfig;
    }

    /**
     * 日志写入文件相关配置
     * @return LogConfig
     */
    public static Log2FileConfig getLog2FileConfig() {
        return log2FileConfig;
    }

    public static Printer tag(String tag) {
        return printer.setTag(tag);
    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {
        printer.v(msg, args);
    }

    public static void v(Object object) {
        printer.v(object);
    }


    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        printer.d(msg, args);
    }


    /**
     * debug输出
     *
     * @param tag
     * @param content
     */
    public static void d(String tag, Object content) {
        printer.setTag(tag).d(content);
    }


    public static void d(Object object) {
        printer.d(object);
    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {
        printer.i(msg, args);
    }

    /**
     * info输出
     *
     * @param tag
     * @param content
     */
    public static void i(String tag, Object content) {
        printer.setTag(tag).i(content);
    }

    public static void i(Object object) {
        printer.i(object);
    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {
        printer.w(msg, args);
    }

    public static void w(Object object) {
        printer.w(object);
    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {
        printer.e(msg, args);
    }

    public static void e(Object object) {
        printer.e(object);
    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {
        printer.wtf(msg, args);
    }

    public static void wtf(Object object) {
        printer.wtf(object);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        printer.json(json);
    }

    /**
     * 输出xml
     * @param xml
     */
    public static void xml(String xml) {
        printer.xml(xml);
    }



}
