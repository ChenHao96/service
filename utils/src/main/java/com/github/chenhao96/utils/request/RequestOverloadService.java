package com.github.chenhao96.utils.request;

public interface RequestOverloadService {

    boolean checkRequestOverLoad(String userName, String deviceId, String url, long lastTime);
}
