package com.tourcoo.core.config;


import android.text.TextUtils;

import com.tourcoo.core.log.LogLevel;
import com.tourcoo.core.log.Parser;
import com.tourcoo.core.log.parser.ParserManager;
import com.tourcoo.core.log.pattern.LogPattern;

/**
 * Created by pengwei on 16/3/4.
 * Log config
 */
public class LogConfigImpl implements LogConfig {

    private boolean enable = true;
    private String tagPrefix;
    private boolean showBorder = true;
    @LogLevel.LogLevelType
    private int logLevel = LogLevel.TYPE_VERBOSE;
    private String formatTag;
    private int methodOffset = 0;

    private static LogConfigImpl singleton;

    private LogConfigImpl() {
    }

   public static LogConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (LogConfigImpl.class) {
                if (singleton == null) {
                    singleton = new LogConfigImpl();
                }
            }
        }
        return singleton;
    }

    @Override
    public LogConfig configAllowLog(boolean allowLog) {
        this.enable = allowLog;
        return this;
    }

    @Override
    public LogConfig configTagPrefix(String prefix) {
        this.tagPrefix = prefix;
        return this;
    }

    @Override
    public LogConfig configFormatTag(String formatTag) {
        this.formatTag = formatTag;
        return this;
    }

    public  String getFormatTag(StackTraceElement caller) {
        if (TextUtils.isEmpty(formatTag)) {
            return null;
        }
        return LogPattern.compile(formatTag).apply(caller);
    }

    @Override
    public LogConfig configShowBorders(boolean showBorder) {
        this.showBorder = showBorder;
        return this;
    }

    @Override
    public LogConfig configMethodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public  int getMethodOffset() {
        return methodOffset;
    }

    @Override
    public LogConfig configLevel(@LogLevel.LogLevelType int logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    @SafeVarargs
    @Override
    public final LogConfig addParserClass(Class<? extends Parser>... classes) {
        ParserManager.getInstance().addParserClass(classes);
        return this;
    }

   public boolean isEnable() {
        return enable;
    }

    public  String getTagPrefix() {
        if (TextUtils.isEmpty(tagPrefix)) {
            return "LogUtils";
        }

        return tagPrefix;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

   public int getLogLevel() {
        return logLevel;
    }
}
