package com.huidong.legalsys.domain;

/**
 * 用户登录表单
 * phone 用户的手机号
 * attempt 用户尝试登录的次数
 * status 用户登录状态
 *  @see com.huidong.legalsys.enumeration.LoginStatusEnum
 * freezetime 用户账号冻结的时间
 */

public class Login {
    private String phone;
    private Integer attempt;
    private Integer status;
    private String freezetime;

    @Override
    public String toString(){
        return "LoginLog{" +
                "phone=" + phone +
                ",attempt=" + attempt +
                ",status=" + status +
                ",freezeTime=" + freezetime + "}";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setFreezeTime(String freezeTime) {
        this.freezetime = freezeTime;
    }
}
