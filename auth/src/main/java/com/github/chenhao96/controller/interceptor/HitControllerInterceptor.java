package com.github.chenhao96.controller.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.chenhao96.utils.ReadJsonFileUtil;
import com.github.chenhao96.utils.Utils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HitControllerInterceptor extends AbstractInterceptor {

    @Value("${fail_login_count}")
    private Integer failLoginCount;

    @Value("${fail_login_waitTime}")
    private Long waitTime;

    private long lastReadTime = 0L;

    private static ReadJsonFileUtil util = ReadJsonFileUtil.newInstance("hitIpConfig");

    private static Map<String, AtomicInteger> record = new HashMap<>(100);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        readConfig();
        final String ip = Utils.getIp(request);
        AtomicInteger count = record.get(ip);
        if (count != null && count.get() >= failLoginCount) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    clearFailLoginCount(ip);
                }
            }, waitTime);
            response.setStatus(401);
            return false;
        }

        return true;
    }

    private void readConfig() {
        long time = System.currentTimeMillis();
        if (time - lastReadTime > TimeUnit.SECONDS.toMillis(60)) {
            lastReadTime = time;
            JsonNode node = util.getNode(true);
            if (node == null || node.elements() == null) return;
            Iterator<JsonNode> iterator = node.elements();
            while (iterator.hasNext()) {
                JsonNode ip = iterator.next();
                clearFailLoginCount(ip.asText());
            }
        }
    }

    public static void incrementFailLoginCount(String ip) {

        AtomicInteger count = record.get(ip);
        if (count == null) {
            count = new AtomicInteger();
        }
        count.incrementAndGet();
        record.put(ip, count);
    }

    public static void clearFailLoginCount(String ip) {
        AtomicInteger count = record.get(ip);
        if (count != null) {
            count.set(0);
            record.put(ip, count);
        }
    }
}
