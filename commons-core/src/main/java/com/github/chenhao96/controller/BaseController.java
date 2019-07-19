package com.github.chenhao96.controller;

import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.controller.DatePropertyEditorSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController extends AbstractController {

    public static final String CLIENT_IP_KEY = "AbstractController_CLIENT_IP";
    public static final String CLIENT_PORT_KEY = "AbstractController_CLIENT_PORT";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
    }

    protected void responseJsonOrJsonp(ServiceResult result) {
        if (result != null) {
            super.responseJsonp(result.isResult(), result.getResponseCode(), result.getMessage(), result.getReturnData());
        } else {
            getResponse().setStatus(401);
        }
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSessionAttribute(String key) {
        return (T) getSession().getAttribute(key);
    }

    protected Map<String, String> getRequestParam() {
        return getRequestParam(getRequest().getParameterMap());
    }

    public static Map<String, String> getRequestParam(Map<String, String[]> reqMap) {
        if (CollectionUtils.isEmpty(reqMap)) return null;
        Map<String, String> resultMap = new HashMap<>(reqMap.size());
        for (Map.Entry<String, String[]> entry : reqMap.entrySet()) {
            String[] values = entry.getValue();
            if (values == null || values.length == 0) continue;
            resultMap.put(entry.getKey(), values[0]);
        }
        return resultMap;
    }

    protected String getRequestClientIP() {
        return (String) getRequest().getAttribute(CLIENT_IP_KEY);
    }

    protected int getRequestClientPort() {
        return (int) getRequest().getAttribute(CLIENT_PORT_KEY);
    }

}
