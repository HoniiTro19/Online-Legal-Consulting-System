package com.huidong.legalsys.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.List;
import java.util.Map;

@Component
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String ip = (String) session.getAttribute("ip");
        ServletContext servletContext = session.getServletContext();
        Map<String, List<HttpSession>> userMap = (Map<String, List<HttpSession>>) servletContext.getAttribute("userMap");
        List<HttpSession> sessions = userMap.get(ip);
        sessions.remove(session);
        if(sessions.size()==0){
            userMap.remove(ip);
        }else{
            userMap.put(ip, sessions);
        }
        servletContext.setAttribute("userMap", userMap);

    }
}