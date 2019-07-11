package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.controller.AbstractController;
import com.github.chenhao96.converter.MappingJackson2HttpMessageConverter;
import com.github.chenhao96.utils.StringUtils;
import com.github.chenhao96.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class RequestInterceptor extends AbstractInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();
        String address = Utils.getIPAndPort(request);
        String param = catalinaMap2String(request.getParameterMap());
        String callBackName = request.getParameter(AbstractController.CALLBACK_HTTP_PARAMETER_NAME);
        if (StringUtils.isNotEmpty(callBackName))
            MappingJackson2HttpMessageConverter.setJsonPCallBackName(callBackName);
        LOGGER.info("request address:{},requestUrl:{},param:{}", address, requestUrl, param);
        return true;
    }

    private String catalinaMap2String(Map map) {

        Set keys = map.keySet();
        StringBuilder msg = new StringBuilder("{");
        if (keys.size() > 0) {

            for (Object key : keys) {
                msg.append(",");
                msg.append(key);
                msg.append(":");
                msg.append(Arrays.toString((Object[]) map.get(key)));
            }

            if (msg.length() > 0) {
                msg.delete(1, 2);
            }
        }
        msg.append("}");
        return msg.toString();
    }
}
