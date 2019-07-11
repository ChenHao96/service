package com.github.chenhao96.service.impl;

import com.github.chenhao96.utils.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogServiceImpl implements LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public void debug(String tag, Object param, Object retValue, Exception ex) {
        LOGGER.debug("tag:{},param:{},return:{}", tag, param, retValue, ex);
    }

    @Override
    public void info(String tag, Object param, Object retValue, Exception ex) {
        LOGGER.info("tag:{},param:{},return:{}", tag, param, retValue, ex);
    }

    @Override
    public void warring(String tag, Object param, Object retValue, Exception ex) {
        LOGGER.warn("tag:{},param:{},return:{}", tag, param, retValue, ex);
    }

    @Override
    public void error(String tag, Object param, Object retValue, Exception ex) {
        LOGGER.error("tag:{},param:{},return:{}", tag, param, retValue, ex);
    }
}
