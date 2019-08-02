package com.github.chenhao96.model.enumeration;

public enum HttpStatusEnum {

    API_VERSION_TOO_LOW(-5, "接口版本过低!"),
    SYSTEM_ERROR(-4, "系统忙，请稍候再试！"),
    REQUEST_OVERLOAD(-3, "请求过于频繁，请稍候再试！"),
    FILE_TOO_LARGE(-2, "上传文件过大！"),
    PARAM_FAULT(-1, "参数异常！"),
    SUCCESS(0, "请求成功！"),

    CREATE_PAY_ORDER_SUCCESS(10000, "创建支付订单成功！"),
    PAY_ORDER_EXIST(10001, "充值订单已存在，不能重复操作!"),
    USER_ID_NOT_EXIST(10002, "充值用户ID不存在，请查证在试!"),
    QUERY_PAY_SUCCESS(10003, "充值成功!"),
    QUERY_PAY_WAIT(10004, "充值失败!"),
    CREATE_PAY_ORDER_FAULT(10005, "创建支付订单失败!"),
    RECHARGE_GOLD_FAIL_COUNT(10006, "充值游戏币数不正确，请稍候再试!"),
    RECHARGE_ORDER_NUMBER_NOT_EXIST(10007, "充值订单号不存在!"),
    APPLE_RECEIPT_FAIL(10008, "非法的支付验证，该验证凭据不是正确的!"),

    LOGIN_SUCCESS(20000, "登录成功！"),
    LOGIN_OVER_TIME(20001, "登录超时,请重新登录!"),
    USERNAME_OR_PASSWORD_FAULT(20002, "用户名或密码不正确！"),
    URL_PERMISSION_FAULT(20003, "您没有该访问权限，请联系管理员！"),
    INFO_UPDATE_FAULT(20004, "信息修改失败，请稍候再试！"),
    CONFIRM_PASSWORD_NOT_SAME(20005, "两次密码不一致!"),
    LOGIN_PASSWORD_FAULT(20006, "登录密码不正确！"),
    REPEAT_AUTHORIZATION(20007, "重复授权!"),
    NO_MEMBER_INFO(20008, "用户信息未找到,或该id是机器人id!"),
    GAME_ID_USED(20009, "该GameId已被使用!"),

    PHONE_NUMBER_INCORRECT(30000, "手机号码不正确或您使用的号码过新!"),
    PHONE_NUMBER_EXISTENCE(30001, "手机号码已被使用，请更换手机号!"),
    GAME_ID_INCORRECT(30002, "GameId不正确!"),
    USER_RED_PACKAGE_MONEY_FAULT(30003, "用户红包金额异常!"),
    USER_NOT_BINDING_WECHAT_PUBLIC(30004, "请先关注微信公众号!"),
    REVIEW_TRANSFER_REBATE_RECORD_FAULT(40000, "数据审核失败，请刷新后再试!"),

    QUERY_USER_NOT_EXIST(50000, "未找到与您微信匹配的游戏账号!"),
    QUERY_USER_INFO_NOT_EXIST(50001, "未找到与您输入匹配的帐号!");

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
