package com.huidong.legalsys.domain;

/**
 * 错误实体
 * code 错误代码
 *  @see com.huidong.legalsys.enumeration.ErrorEnum
 * msg 错误提示
 * data 错误内容
 */

public class Error<T> {

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
