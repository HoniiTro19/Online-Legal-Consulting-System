package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDao {
    Boolean newSession(Session session);
    Boolean setStatus(Integer status, Integer id);
    List<Session> getAllSessions();
}
