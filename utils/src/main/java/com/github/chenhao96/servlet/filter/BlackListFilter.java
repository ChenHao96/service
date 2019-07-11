package com.github.chenhao96.servlet.filter;

import com.github.chenhao96.utils.JsonUtils;
import com.github.chenhao96.utils.StringUtils;
import com.github.chenhao96.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackListFilter implements Filter {

    //ArrayList默认10个容器长度
    private int max_list_length = 10;
    private long last_Update_Time = 0L;
    private List<String> back_ip_list = new ArrayList<>(max_list_length);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String ip = Utils.getIp((HttpServletRequest) servletRequest);
        queryBackList();
        if (!back_ip_list.contains(ip)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(410);
    }

    private void queryBackList() throws IOException {

        String u = BlackListFilter.class.getResource("/").getPath();
        File backList = new File(u, "backlist.json");
        long modified = backList.lastModified();
        if (last_Update_Time != modified) {
            last_Update_Time = modified;
            String json = Utils.file2String(backList);
            if (StringUtils.isNotEmpty(json)) {
                String[] ips = JsonUtils.jsonStr2Object(json, String[].class);
                if (ips != null && ips.length > 0) {
                    if (ips.length > max_list_length) {
                        max_list_length = ips.length;
                        back_ip_list = new ArrayList<>(max_list_length);
                    } else {
                        back_ip_list.clear();
                    }
                    back_ip_list.addAll(Arrays.asList(ips));
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
