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

package com.github.chenhao96.controller;

import com.github.chenhao96.controller.interceptor.LoginSingleProcessInterceptor;
import com.github.chenhao96.model.LoginUserInfo;
import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginInfoController extends BaseController {

    @RequestMapping(value = "/onlineUser")
    public void onlineUser() {

        ServiceResult<List<LoginUserInfo>> serviceResult = new ServiceResult<>();
        serviceResult.setResult(true);

        String sessionId = getSession().getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Iterator<String> stringIterator = LoginSingleProcessInterceptor.USER_NAME_SET.iterator();
        List<LoginUserInfo> list = new ArrayList<>(LoginSingleProcessInterceptor.USER_NAME_SET.size());

        ServletContext context = getSession().getServletContext();
        while (stringIterator.hasNext()) {
            LoginUserInfo info = (LoginUserInfo) context.getAttribute(stringIterator.next());
            if (info == null) continue;
            LoginUserInfo info1 = new LoginUserInfo(info.getUserName());
            info1.setStatus(info.getStatus());
            info1.setCurrentIp(info.getCurrentIp());
            info1.setCurrentLoginTimeStr(sdf.format(info.getCurrentLoginTime()));
            if (StringUtils.isNotEmpty(info.getLastIp())) {
                info1.setLastIp(info.getLastIp());
            } else {
                info1.setLastIp("-");
            }
            if (info.getLastLoginTime() == null) {
                info1.setLastLoginTimeStr("-");
            } else {
                info1.setLastLoginTimeStr(sdf.format(info.getLastLoginTime()));
            }
            if (sessionId.equals(info.getSessionId())) {
                info1.setStatus(2);
            }
            list.add(info1);
        }

        serviceResult.setReturnData(list);
        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/offlineUser")
    public void offlineUser(String userName) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);

        ServletContext context = getSession().getServletContext();
        LoginUserInfo info = (LoginUserInfo) context.getAttribute(LoginSingleProcessInterceptor.ONLINE_USER_PREFIX_KEY + userName);
        if (info != null) {
            info.setStatus(0);
            info.setSessionId(null);
        }

        responseJsonOrJsonp(serviceResult);
    }

    @RequestMapping(value = "/lockUser")
    public void lockUser(String userName, boolean status) {

        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResult(true);

        ServletContext context = getSession().getServletContext();
        LoginUserInfo info = (LoginUserInfo) context.getAttribute(LoginSingleProcessInterceptor.ONLINE_USER_PREFIX_KEY + userName);
        if (info != null) {
            info.setStatus(status ? -1 : 0);
            info.setSessionId(null);
        }

        responseJsonOrJsonp(serviceResult);
    }
}