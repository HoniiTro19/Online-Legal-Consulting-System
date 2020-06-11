package com.huidong.legalsys.domain;

import org.hibernate.validator.constraints.Length;

/**
 * 用户表单
 * phone 用户联系电话（作为登录系统的id）
 * name 用户真实姓名
 * password 用户登陆密码
 * idno 用户身份证号
 * licenseurl 用户律师证明地址
 * firmname 用户律所公司名
 */

public class User {
    @Length(min=11,max=11,message = "手机号为11位数字字符串")
    private String phone;
    private String name;
    private String password;
    @Length(min=28,max=28,message = "身份证号为28为数字字符串")
    private String idno;
    private String licenseurl;
    private String firmname;

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", name=" + name +
                ", password=" + password +
                ", idno=" + idno +
                ", licenseurl=" + licenseurl +
                ", firmname=" + firmname + "}";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl;
    }

    public String getFirmname() {
        return firmname;
    }

    public void setFirmname(String firmname) {
        this.firmname = firmname;
    }
}
