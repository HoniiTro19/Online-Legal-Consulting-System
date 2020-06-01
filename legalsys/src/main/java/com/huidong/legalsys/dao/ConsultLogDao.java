package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.ConsultLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 用户咨询日志的数据访问类
 * newConsultLog 新建咨询
 *  @param: consultLog 用户咨询信息
 *  @return: ConsultLog 用户咨询信息
 *
 * getConsultLogsByPhone 根据手机号查询用户咨询日志
 *  @param: phone 手机号
 *  @return: List<ConsultLog> 用户咨询信息列表
 *
 * getConsultLogsByType 根据咨询类型查询用户咨询日志
 *  @param: type 咨询类型
 *  @return: List<ConsultLog> 用户咨询信息列表
 *
 * getTopkConsultLogs 获得时间最近的k个咨询记录
 *  @param: topk k值
 *  @return: List<ConsultLog> 用户咨询信息列表
 *
 * getAllConsultLogs 获得所有用户咨询记录
 *  @param: phone, newfirmname 手机号，新公司名字
 *
 * getAllUsers 获得所有注册律师的信息
 *  @return List<User> 用户信息列表
 */

@Repository
public interface ConsultLogDao {
    Boolean newConsultLog(ConsultLog consultLog);
    List<ConsultLog> getConsultLogsByPhone(String phone);
    List<ConsultLog> getConsultLogsByType(Integer type);
    List<ConsultLog> getTopkConsultLogs();
    List<ConsultLog> getAllConsultLogs();
}
