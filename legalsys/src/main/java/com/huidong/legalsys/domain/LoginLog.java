package com.huidong.legalsys.domain;

/*
 * 用户登录日志
 * phone 登录用户的手机号
 * ip 登录用户的ip地址
 * attempt 用户尝试登录的次数
 * status 用户登录状态
 * timeLatest 用户最后登录的时间
 */

public class LoginLog {
    private String phone;
    private String ip;
    private Integer attempt;
    private Integer status;
    private String timeLatest;

    @Override
    public String toString(){
        return "LoginLog{" +
                "phone=" + phone +
                ",ip=" + ip +
                ",attempt=" + attempt +
                ",status=" + status +
                ",timeLatest=" + timeLatest + "}";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTimeLatest() {
        return timeLatest;
    }

    public void setTimeLatest(String timeLatest) {
        this.timeLatest = timeLatest;
    }
}
