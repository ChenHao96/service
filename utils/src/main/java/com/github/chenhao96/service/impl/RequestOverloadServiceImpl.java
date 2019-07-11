package com.github.chenhao96.service.impl;

import com.github.chenhao96.utils.request.RequestOverloadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestOverloadServiceImpl implements RequestOverloadService {

    private Map<String, Map<String, Long>> redis = new HashMap<>();
    private Map<String, List<Map<String, Object>>> infos = new HashMap<>();

    private Long maxRequestTime;

    public Long getMaxRequestTime() {
        return maxRequestTime;
    }

    public void setMaxRequestTime(Long maxRequestTime) {
        this.maxRequestTime = maxRequestTime;
    }

    @Override
    public boolean checkRequestOverLoad(String userName, String deviceId, String url, long lastTime) {

        boolean ret = false;
        Map<String, Long> ipMap = redis.get(deviceId);
        if (ipMap == null) {
            ipMap = new HashMap<>();
        }

        Long time = ipMap.get(url);
        if (time != null) {
            if (lastTime - time < maxRequestTime) {
                ret = true;
            }
        }

        ipMap.put(url, lastTime);
        redis.put(deviceId, ipMap);

        List<Map<String, Object>> is = infos.get(deviceId);
        if (is == null) {
            is = new ArrayList<>();
        }
        Map<String, Object> inf = new HashMap<>(2);
        inf.put("url", url);
        inf.put("lastTime", lastTime);
        is.add(inf);
        infos.put(deviceId, is);

        return ret;
    }
}
