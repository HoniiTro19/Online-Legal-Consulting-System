package com.huidong.legalsys.enumeration;

public enum ErrorEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    REGISTER_ERROR(100, "该手机号已被注册"),
    NOTREGISTER_ERROR(101,"该手机号还未注册"),
    PASSWORD_ERROR(102, "账号密码不匹配"),
    FREEZE_ERROR(103, "连续5次密码错误，账号已被冻结10分钟，请稍后再试"),
    AUTH_ERROR(104, "律师认证失败，请正确提交律师执照以及律所信息"),
    LAWNOTFOUND_ERROOR(105, "检索的法条不存在"),
    NOTLAWYER_ERROR(106, "经过律师认证才可以执行此操作"),
    SESSIONNOTEXIST_ERROR(107, "讨论区不存在该会话"),
    NETWROK_ERROR(108, "网络异常");

    private Integer code;
    private String msg;

    ErrorEnum(Integer code, String msg) {
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