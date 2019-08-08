package com.github.chenhao96.model.enumeration;

public enum HttpStatusEnum {

    SYSTEM_ERROR(-4, "系统忙，请稍候再试！"),
    PARAM_FAULT(-1, "参数异常！"),
    SUCCESS(0, "请求成功！"),
    LOGIN_SUCCESS(20000, "登录成功！"),
    LOGIN_OVER_TIME(20001, "登录超时,请重新登录!"),
    USERNAME_OR_PASSWORD_FAULT(20002, "用户名或密码不正确！"),
    URL_PERMISSION_FAULT(20003, "您没有该访问权限，请联系管理员！"),
    INFO_UPDATE_FAULT(20004, "信息修改失败，请稍候再试！"),
    CONFIRM_PASSWORD_NOT_SAME(20005, "两次密码不一致!"),
    LOGIN_PASSWORD_FAULT(20006, "登录密码不正确！"),
    REPEAT_AUTHORIZATION(20007, "重复授权!");

    private int code;
    private String description;

    HttpStatusEnum(int code, String description) {
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
