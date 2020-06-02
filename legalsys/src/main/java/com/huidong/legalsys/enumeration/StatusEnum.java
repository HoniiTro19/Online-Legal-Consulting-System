package com.huidong.legalsys.enumeration;

public enum StatusEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    REGISTER_ERROR(100, "手机号已注册"),
    NOTREGISTER_ERROR(101,"手机号未注册"),
    PASSWORD_ERROR(102, "账号密码不匹配"),
    FREEZE_ERROR(103, "账号已被冻结"),
    NETWROK_ERROR(104, "网络异常");

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}