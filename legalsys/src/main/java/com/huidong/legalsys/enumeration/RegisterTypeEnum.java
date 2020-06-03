package com.huidong.legalsys.enumeration;

public enum RegisterTypeEnum {
    NORMAL(0),
    LAWYER(1);

    private Integer type;

    RegisterTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType(){return type;}

}
