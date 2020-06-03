package com.huidong.legalsys.enumeration;

public enum ConsultTypeEnum {
    LAW(0),
    PENALTY(1);

    private Integer type;

    ConsultTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType(){return type;}
}
