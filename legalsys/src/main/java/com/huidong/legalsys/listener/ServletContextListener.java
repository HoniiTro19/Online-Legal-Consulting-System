package com.huidong.legalsys.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServletContextListener implements javax.servlet.ServletContextListener {

    private Map<String, List<HttpSession>> userMap = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userMap",userMap);
    }
}