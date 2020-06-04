package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 讨论区咨询信息的数据访问类
 */

@Repository
public interface SessionDao {

    /**
     * newSession 创建新的咨询
     * @param session 新的咨询信息
     * @return Boolean 是否成功创建
     */
    Boolean newSession(Session session);

    /**
     * isExist 判断讨论区咨询是否存在
     * @param id 咨询编号
     * @return Integer 讨论区咨询是否存在
     */
    Integer isExist(Integer id);

    /**
     * getPhone 获得咨询者的手机号
     * @param id 咨询编号
     * @return String 咨询者的手机号
     */
    String getPhone(Integer id);

    /**
     * getTitle 获得咨询标题
     * @param id 咨询编号
     * @return String 咨询标题
     */
    String getTitle(Integer id);

    /**
     * getContent 获得咨询内容
     * @param id 咨询编号
     * @return String 咨询内容
     */
    String getContent(Integer id);

    /**
     * setStatus 设置咨询状态
     * @param id 咨询编号
     * @param status 咨询状态
     * @return Boolean 是否成功设置
     */
    Boolean setStatus(Integer id, Integer status);

    /**
     * getAllSessions 获得所有未被答复的咨询信息
     * @return List<Session> 所有未被答复的咨询信息
     */
    List<Session> getAllSessions();
}
