package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Session;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 讨论区会话的控制层
 */
@Controller
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     *
     * @param map
     * @return
     */
    @GetMapping("/session")
    public String sessions(Map<String, Object> map){
        List<Session> session = sessionService.getAllSessions();
        map.put("session", session);
        return "session/index";
    }

    @PostMapping("/session/newsession")
    public String newsession(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        sessionService.newsession(phone, title, content);
        return "session/newsession";
    }

    @PostMapping("/session/estbconvr")
    public String estbconvr(@RequestParam("sessionid") Integer sessionid,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String lawyerphone = user.getPhone();
        sessionService.estbConvr(lawyerphone, sessionid);
        return "redirect:/session/index";
    }
}
