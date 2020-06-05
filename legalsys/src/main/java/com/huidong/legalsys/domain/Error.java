package com.huidong.legalsys.domain;

/**
 * 错误实体
 * code 错误代码
 *  @see com.huidong.legalsys.enumeration.ErrorEnum
 * msg 错误提示
 */

public class Error<T> {

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {return msg;}

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
