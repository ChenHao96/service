/**
 * Copyright 2019 ChenHao96
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.chenhao96.servlet.listener;


import com.github.chenhao96.controller.interceptor.InspectionSignatureInterceptor;
import com.github.chenhao96.controller.interceptor.LoginSingleProcessInterceptor;
import com.github.chenhao96.model.LoginUserInfo;
import com.github.chenhao96.utils.JsonUtils;
import com.github.chenhao96.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String userName = (String) session.getAttribute(InspectionSignatureInterceptor.USERNAME);
        if (StringUtils.isNotEmpty(userName)) {
            ServletContext context = session.getServletContext();
            String key = LoginSingleProcessInterceptor.ONLINE_USER_PREFIX_KEY + userName;
            LoginUserInfo info = (LoginUserInfo) context.getAttribute(key);
            if (info != null) {
                String path = (String) session.getAttribute(LoginSingleProcessInterceptor.ACCOUNT_LOGIN_TMP_PATH);
                writeLoginObj(path, info);
                if (session.getId().equals(info.getSessionId())) {
                    info.setStatus(0);
                }
            }
        }
    }

    private void writeLoginObj(String parent, LoginUserInfo info) {

        if (info == null) return;
        if (StringUtils.isEmpty(parent)) return;
        File userLogin = new File(parent, info.getUserName() + ".login");
        FileOutputStream fos = null;
        try {
            String body = JsonUtils.object2Json(info);
            fos = new FileOutputStream(userLogin);
            fos.write(body.getBytes(), 0, body.length());
            fos.flush();
        } catch (IOException ignored) {
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}