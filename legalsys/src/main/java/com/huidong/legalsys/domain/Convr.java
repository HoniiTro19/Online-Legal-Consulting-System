package com.huidong.legalsys.domain;

/**
 * 用户会话表单
 * id 会话编号
 * phone 被受理用户的手机号
 * lawyerphone 受理律师的手机号
 * convr 被受理用户的会话内容
 * lawyerconvr 受理律师的会话内容
 * time 被受理用户的会话时间
 * lawyertime 受理用户的会话时间
 */

public class Convr {
    private Integer id;
    private String phone;
    private String lawyerphone;
    private String convr;
    private String lawyerconvr;
    private String time;
    private String lawyertime;

    @Override
    public String toString(){
        return "ConvrLog{" +
                "id=" + id +
                ",phone=" + phone +
                ",lawyerphone=" + lawyerphone +
                ",convr=" + convr +
                ",lawyerconvvr=" + lawyerconvr +
                ",time=" + time +
                ",lawyertime=" + lawyertime + "}";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLawyerphone(String lawyerphone) {
        this.lawyerphone = lawyerphone;
    }

    public void setConvr(String convr) {
        this.convr = convr;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
