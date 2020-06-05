package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户信息表的数据访问类
 */
@Repository
public interface UserDao {
    /**
     * @Description 判断用户是否注册
     * @param phone 用户电话号码
     * @return String 用户电话号码
     */
    String isRegisted(String phone);

    /**
     * @Description 判断该用户是否是律师
     * @param phone 用户电话号码
     * @return String 用户电话号码
     */
    String isRegistedLawyer(String phone);

    /**
     * @Description 用户注册
     * @param user 用户信息
     * @return Boolean 是否成功登记
     */
    Boolean register(User user);

    /**
     * @Description 手机号登录
     * @param phone 手机号
     * @param password 密码
     * @return User 用户个人信息
     */
    User login(String phone, String password);

    /**
     * @Description 获取用户密码
     * @param phone 手机号
     * @return String 密码
     */
    String getPassword(String phone);

    /**
     * @Description 重置密码
     * @param phone 手机号
     * @param newpassword 新密码
     * @return Boolean 是否设置成功
     */
    Boolean setPassword(String phone, String newpassword);

    /**
     * @Description 重置律师执照
     * @param phone 手机号
     * @param newlicenseurl 新律师执照
     * @return Boolean 是否设置成功
     */
    Boolean setLicenseurl(String phone, String newlicenseurl);

    /**
     * @Description 重置律所信息
     * @param phone 手机号
     * @param newfirmname 新律所信息
     * @return Boolean 是否设置成功
     */
    Boolean setFirmname(String phone, String newfirmname);

    /**
     * @Description 获得所有注册用户信息
     * @return List<User> 所有注册用户信息
     */
    List<User> getAllUsers();
}