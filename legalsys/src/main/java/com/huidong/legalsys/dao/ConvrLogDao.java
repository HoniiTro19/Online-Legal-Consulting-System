package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.ConvrLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 会话日志的数据访问类
 * isestablished 查询用户之间是否建立了咨询
 *  @param: phone, lawyerphone 普通用户手机号，律师用户手机号
 *  @return: ConvrLog 会话日志信息
 *
 * newConvrLog 新建用户会话
 *  @param: convrLog 用户会话日志信息
 *  @return: Boolean
 *
 * addConvr 增加普通用户的会话内容
 *  @param: convr 普通用户的会话内容
 *  @return: Boolean
 *
 * addLawyerConvr 增加律师用户的会话内容
 *  @param: lawyerconvr 律师用户的会话内容
 *  @return: Boolean
 *
 * addTime 增加普通用户的会话内容
 *  @param: time 普通用户的会话时间
 *  @return: Boolean
 *
 * addTime 增加律师用户的会话内容
 *  @return lawyertime 律师用户的会话时间
 *  @return: Boolean
 */

@Repository
public interface ConvrLogDao {
    ConvrLog isestablished(String phone, String lawyerphone);
    Boolean newConvrLog(ConvrLog convrLog);
    String getConvr(String phone, String lawyerphone);
    Boolean setConvr(String convr, String phone, String lawyerphone);
    String getLawyerConvr(String phone, String lawyerphone);
    Boolean setLawyerConvr(String lawyerconvr, String phone, String lawyerphone);
    String getTime(String phone, String lawyerphone);
    Boolean setTime(String time, String phone, String lawyerphone);
    String getLawyerTime(String phone, String lawyerphone);
    Boolean setLawyerTime(String lawyertime, String phone, String lawyerphone);
    List<ConvrLog> getConvrLogsByPhone(String phone);
    List<ConvrLog> getConvrLogsByLawyerPhone(String lawyerphone);
    List<ConvrLog> getAllConvrLogs();
}
