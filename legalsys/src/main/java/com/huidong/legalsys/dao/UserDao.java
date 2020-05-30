package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 用户信息表的数据访问类
 * register 用户注册
 *  @param: user 用户信息
 *
 * loginByPhone 手机号登录
 *  @param: phone, password 手机号，密码
 *  @Return: User 用户个人信息
 *
 * changePassword 修改密码
 *  @param: phone, newpassword 手机号，新密码
 *
 * uploadLicenseurl 上传律师执照
 *  @param: phone, newlicenseurl 手机号，新律师执照url
 *
 * uploadFirmname 上传律所名称
 *  @param: phone, newfirmname 手机号，新公司名字
 *
 * getAllUsers 获得所有注册律师的信息
 *  @return List<User> 用户信息列表
 */

@Repository
public interface UserDao {
    User isRegisted(String phone);
    void register(User user);
    User login(String phone, String password);
    void changePassword(String phone, String newpassword);
    void uploadLicenseurl(String phone, String newlicenseurl);
    void uploadFirmname(String phone, String newfirmname);
    List<User> getAllUsers();
}