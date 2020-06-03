package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Convr;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 会话日志的数据访问类
 * isestablished 查询用户之间是否建立了咨询
 *  @param: phone, lawyerphone 普通用户手机号，律师用户手机号
 *  @return: ConvrLog 会话日志信息
 *
 * newConvr 新建用户会话
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
public interface ConvrDao {
    Convr isestablished(String phone, String lawyerphone);
    Boolean newConvr(Convr convr);
    String getConvr(Integer id);
    Boolean setConvr(String convr, Integer id);
    String getLawyerConvr(Integer id);
    Boolean setLawyerConvr(String lawyerconvr, Integer id);
    String getTime(Integer id);
    Boolean setTime(String time, Integer id);
    String getLawyerTime(Integer id);
    Boolean setLawyerTime(String lawyertime, Integer id);
    List<Convr> getConvrsByPhone(String phone);
    List<Convr> getConvrsByLawyerPhone(String lawyerphone);
    Boolean deleteConvr(Integer id);
    List<Convr> getAllConvrs();
}
