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
    VERIFYNOTMATCH(108, "登录密码前后不一致"),
    IDNO_ERROR(109, "该身份证已被注册使用"),
    ESTBCONVR_ERROR(110, "不能与自己建立会话"),
    CONSULT_ERROR(111, "未能成功预测相关法条与刑期"),
    PHONELEN_ERROR(112, "请输入11位格式正确的手机号"),
    IDNOLEN_ERROR(113, "请输入18位或15位正确的中国大陆居民身份证");


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