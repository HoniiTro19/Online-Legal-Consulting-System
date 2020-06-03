package com.huidong.legalsys.enumeration;

public enum SessionStatusEnum {
    ESTABLISHED(0),
    UNESTABLISHED(1);

    private Integer status;

    SessionStatusEnum(Integer status){
        this.status = status;
    }

    public Integer getStatus(){return status;}
}
