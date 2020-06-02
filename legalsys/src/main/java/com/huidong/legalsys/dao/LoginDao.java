package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Login;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/* 登录日志表的数据访问类
 * isLogined 查询是否有登陆记录
 *  @param: phone 用户手机号
 *  @return: LoginLog 用户登录日志
 *
 * setIp 设置用户的ip地址
 *  @param: phone, ip 手机号，ip地址
 *
 * setAttempt 增加一次登录尝试次数
 *  @param: phone 手机号
 *
 * setAttempt 设置当前的登录尝试次数
 *  @param: phone, attempt 手机号，尝试次数
 *
 * setStatus 设置用户登录状态 {-1: "账号冻结", 0: "在线", 1:, "离线"}
 *  @param: phone 手机号
 *
 * setTimeLatest 设置用户最后一次登录的时间
 *  @param: phone, timeLatest 手机号，最后登陆时间
 *
 * getAllLogins 获得所有用户的登录日志
 *  @return: List<LoginLog> 所有用户的登录日志
 */

@Repository
public interface LoginDao {
    Boolean newLogin(Login login);
    Login isLogined(String phone);
    Boolean addAttempt(String phone);
    Integer getAttempt(String phone);
    Boolean setStatus(String phone, Integer status);
    Integer getStatus(String phone);
    Boolean setFreezetime(String phone, String freezetime);
    String getFreezetime(String phone);
    List<Login> getAllLogins();
    Integer getConcurrentUsers();
}
