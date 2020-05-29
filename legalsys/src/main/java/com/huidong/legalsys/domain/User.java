package com.huidong.legalsys.domain;

import lombok.Data;

/* 用户表单
 * phone 用户联系电话（作为登录系统的id）
 * nickname 用户昵称
 * name 用户真实姓名
 * password 用户登陆密码
 * idno 用户身份证号
 * licenseurl 用户律师证明地址
 * firmname 用户律所公司名
 * province 用户律所所在省份
 * city 用户律所所在城市
 */

@Data
public class User {
    private String phone;
    private String nickname;
    private String name;
    private String password;
    private String idno;
    private String licenseurl;
    private String firmname;

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", nickname=" + nickname +
                ", name=" + name +
                ", password=" + password +
                ", idno=" + idno +
                ", licenseurl=" + licenseurl +
                ", firmname=" + firmname + "}";
    }
}
