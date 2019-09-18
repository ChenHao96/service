package com.github.chenhao96.service;

import javax.servlet.http.HttpServletRequest;

public interface MessageAuthService {

    String VERIFY_CODE_KEY = "verify_code";

    default boolean contains(String url) {
        return true;
    }

    boolean sendVerifyCodeMessage(HttpServletRequest request);
}
