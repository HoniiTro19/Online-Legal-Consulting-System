package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Login;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 登录日志表的数据访问类
 */
@Repository
public interface LoginDao {
    /**
     * @Description 新建用户登录记录
     * @param login 用户登录信息
     * @return Boolean 是否新建成功
     */
    Boolean newLogin(Login login);

    /**
     * @Description 查询用户是否有登录记录
     * @param phone 手机号
     * @return Login 登录信息
     */
    Login isLogined(String phone);

    /**
     * @Description 增加用户登录失败次数
     * @param phone 手机号
     * @return Boolean 是否增加成功
     */
    Boolean addAttempt(String phone);

    /**
     * @Description 获取用户登录失败次数
     * @param phone 手机号
     * @return Integer 用户登录失败次数
     */
    Integer getAttempt(String phone);

    /**
     * @Description 重置用户的失败次数为0
     * @param phone 手机号
     * @return Boolean 是否重置成功
     */
    Boolean resetAttempt(String phone);

    /**
     * @Description 设置用户账号冻结状态
     * @param phone 手机号
     * @param status 状态
     * @return Boolean 是否设置成功
     */
    Boolean setStatus(String phone, Integer status);

    /**
     * @Description 获取用户账号冻结状态
     * @param phone 手机号
     * @return Integer 用户登录状态
     */
    Integer getStatus(String phone);

    /**
     * @Description 设置用户登录失败时的时间
     * @param phone 手机号
     * @param freezetime 用户登录失败时的时间
     * @return Boolean 是否设置成功
     */
    Boolean setFreezetime(String phone, String freezetime);

    /**
     * @Description 获取最近一次用户登录失败时的时间
     * @param phone 手机号
     * @return String 最近一次用户登录失败时的时间
     */
    String getFreezetime(String phone);
}
