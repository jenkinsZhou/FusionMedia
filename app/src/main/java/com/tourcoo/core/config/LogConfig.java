package com.tourcoo.core.config;

import com.tourcoo.core.log.LogLevel;
import com.tourcoo.core.log.Parser;

/**
 * Created by pengwei on 16/3/4.
 */
public interface LogConfig {

    LogConfig configAllowLog(boolean allowLog);

    LogConfig configTagPrefix(String prefix);

    LogConfig configFormatTag(String formatTag);

    LogConfig configShowBorders(boolean showBorder);

    LogConfig configLevel(@LogLevel.LogLevelType int logLevel);

    LogConfig addParserClass(Class<? extends Parser>... classes);

    LogConfig configMethodOffset(int offset);
}
