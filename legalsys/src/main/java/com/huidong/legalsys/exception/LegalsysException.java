package com.huidong.legalsys.exception;

import com.huidong.legalsys.enumeration.ErrorEnum;

public class LegalsysException extends RuntimeException{

    private Integer code;

    public LegalsysException(ErrorEnum errorEnum){
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
