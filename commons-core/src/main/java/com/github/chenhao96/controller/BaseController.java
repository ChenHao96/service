package com.github.chenhao96.controller;

import com.github.chenhao96.service.ServiceResult;
import com.github.chenhao96.utils.controller.DatePropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpSession;
import java.util.Date;

public abstract class BaseController extends AbstractController {

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
}
