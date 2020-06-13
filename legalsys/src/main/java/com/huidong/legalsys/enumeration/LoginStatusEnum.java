package com.huidong.legalsys.enumeration;

public enum LoginStatusEnum {
    FREEZE(-1),
    NOTFREEZE(0);

    private Integer status;

    LoginStatusEnum(Integer status){
        this.status = status;
    }

    public Integer getStatus(){return status;}
}
