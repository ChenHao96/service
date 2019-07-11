package com.github.chenhao96.model.enumeration;

public enum LogLevelEnum {

    INFO(1, "提示信息"), DEBUG(2, "调试信息"), ERROR(4, "错误信息"), WARRING(3, "警告信息");

    private int code;
    private String description;

    LogLevelEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
