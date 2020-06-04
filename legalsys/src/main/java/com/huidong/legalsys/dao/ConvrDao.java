package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Convr;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会话日志的数据访问类
 */

@Repository
public interface ConvrDao {

    /**
     * newConvr 新建会话
     * @param convr 会话信息
     * @return Boolean 是否成功新建
     */
    Boolean newConvr(Convr convr);

    /**
     * getConvr 获得普通用户在会话中的内容
     * @param id 会话编号
     * @return String 普通用户在会话中的内容
     */
    String getConvr(Integer id);

    /**
     * setConvr 更新普通用户在会话中的内容
     * @param convr 更新后的内容
     * @param id 会话编号
     * @return Boolean 是否成功更新
     */
    Boolean setConvr(String convr, Integer id);

    /**
     * getLawyerConvr 获得律师用户在会话中的内容
     * @param id 会话编号
     * @return String 律师用户在会话中的内容
     */
    String getLawyerConvr(Integer id);

    /**
     * setLawyerConvr 更新律师用户在会话中的内容
     * @param lawyerconvr 更新后的内容
     * @param id 会话编号
     * @return Boolean 是否成功更新
     */
    Boolean setLawyerConvr(String lawyerconvr, Integer id);

    /**
     * getTime 获得普通用户在会话中的时间
     * @param id 会话编号
     * @return String 普通用户在会话中的时间
     */
    String getTime(Integer id);

    /**
     * setTime 更新普通用户在会话中的时间
     * @param time 更新后的时间
     * @param id 会话编号
     * @return Boolean 是否成功更新
     */
    Boolean setTime(String time, Integer id);

    /**
     * getLawyerTime 获得律师用户在会话中的时间
     * @param id 会话编号
     * @return String 律师用户在会话中的时间
     */
    String getLawyerTime(Integer id);

    /**
     * setLawyerTime 更新律师用户在会话中的时间
     * @param lawyertime 更新后的时间
     * @param id 会话编号
     * @return Boolean 是否成功更新
     */
    Boolean setLawyerTime(String lawyertime, Integer id);

    /**
     * getConvrsByPhone 获得普通用户的所有会话内容
     * @param phone 普通用户手机号
     * @return List<Convr> 普通用户的所有会话内容
     */
    List<Convr> getConvrsByPhone(String phone);

    /**
     * getConvrsByLawyerPhone 获得律师用户的所有会话内容
     * @param lawyerphone 律师用户手机号
     * @return List<Convr> 律师用户的所有会话内容
     */
    List<Convr> getConvrsByLawyerPhone(String lawyerphone);

    /**
     * deleteConvr 删除会话内容
     * @param id 会话编号
     * @return Boolean 是否成功删除
     */
    Boolean deleteConvr(Integer id);

    /**
     * getAllConvrs 获得所有会话内容
     * @return 所有会话内容
     */
    List<Convr> getAllConvrs();
}
