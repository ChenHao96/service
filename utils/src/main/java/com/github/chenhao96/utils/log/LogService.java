package com.github.chenhao96.utils.log;

public interface LogService {

    void debug(String tag, Object param, Object retValue, Exception ex);

    void info(String tag, Object param, Object retValue, Exception ex);

    void warring(String tag, Object param, Object retValue, Exception ex);

    void error(String tag, Object param, Object retValue, Exception ex);
}
