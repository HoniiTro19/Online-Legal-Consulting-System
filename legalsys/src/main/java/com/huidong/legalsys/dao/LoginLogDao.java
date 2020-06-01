package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.LoginLog;
import org.springframework.stereotype.Repository;
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
 * setStatus 设置用户登录状态
 *  @param: phone 手机号
 *
 * setTimeLatest 设置用户最后一次登录的时间
 *  @param: phone, timeLatest 手机号，最后登陆时间
 *
 * getAllLoginLogs 获得所有用户的登录日志
 *  @return: List<LoginLog> 所有用户的登录日志
 */

@Repository
public interface LoginLogDao {
    Boolean newLogin(LoginLog loginLog);
    LoginLog isLogined(String phone);
    Boolean setIp(String phone, String ip);
    Boolean addAttempt(String phone);
    Boolean setAttempt(String phone, Integer attempt);
    Boolean setStatus(String phone, Integer status);
    Boolean setTimeLatest(String phone, String timeLatest);
    List<LoginLog> getAllLoginLogs();
    Integer getConcurrentUsers();
}
