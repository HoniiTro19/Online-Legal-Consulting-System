package com.huidong.legalsys.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RequestContextListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        String ip = servletRequest.getRemoteAddr();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        session.setAttribute("ip",ip);
        ServletContext servletContext = sre.getServletContext();
        Map<String, List<HttpSession>> userMap = (Map<String, List<HttpSession>>) servletContext.getAttribute("userMap");
        List<HttpSession> sessionList=userMap.containsKey(ip) ? userMap.get(ip) : new ArrayList<>();
        if(!sessionList.contains(session)){
            sessionList.add(session);
        }
        userMap.put(ip, sessionList);
        servletContext.setAttribute("userMap",userMap);
    }
}