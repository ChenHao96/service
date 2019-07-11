package com.github.chenhao96.utils.controller;

import com.github.chenhao96.utils.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditorSupport extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (StringUtils.isEmpty(text)) {

            setValue(null);
        } else {

            final String[] DATE_FORMAT_STR = {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd"};
            for (String str : DATE_FORMAT_STR) {

                Date data;
                try {

                    data = new SimpleDateFormat(str).parse(text);
                } catch (ParseException e) {
                    continue;
                }

                setValue(data);
                return;
            }

            throw new IllegalArgumentException("时间格式解析异常!");
        }
    }
}
