package com.huidong.legalsys.exception;

import com.huidong.legalsys.enumeration.StatusEnum;

public class LegalsysException extends RuntimeException{

    private Integer code;

    public LegalsysException(StatusEnum statusEnum){
        super(statusEnum.getMsg());
        this.code = statusEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
