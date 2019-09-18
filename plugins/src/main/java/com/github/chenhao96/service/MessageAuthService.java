package com.github.chenhao96.service;

import javax.servlet.http.HttpSession;

public interface MessageAuthService {

    String VERIFY_CODE_KEY = "verify_code";

    boolean contains(String url);

    boolean sendVerifyCodeMessage(HttpSession session);
}
