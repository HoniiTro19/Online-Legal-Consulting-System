package com.huidong.legalsys.domain;

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

    public void setLawyerconvr(String lawyerconvr) {
        this.lawyerconvr = lawyerconvr;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLawyertime(String lawyertime) {
        this.lawyertime = lawyertime;
    }
}
