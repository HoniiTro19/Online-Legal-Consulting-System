package com.huidong.legalsys.enumeration;

public enum LoginStatusEnum {
    FREEZE(-1),
    ONLINE(0),
    OFFLINE(1);

    private Integer status;

    LoginStatusEnum(Integer status){
        this.status = status;
    }

    public Integer getStatus(){return status;}
}
