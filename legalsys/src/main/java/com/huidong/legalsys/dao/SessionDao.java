package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDao {
    Boolean newSession(Session session);
    Integer isExist(Integer id);
    String getPhone(Integer id);
    String getTitle(Integer id);
    String getContent(Integer id);
    Boolean setStatus(Integer id, Integer status);
    List<Session> getAllSessions();
}
