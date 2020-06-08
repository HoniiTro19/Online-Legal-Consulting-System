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
     * @Description 用户进入讨论区，可以看到其他用户提出的问题，可以查看问题的详细情况，也可以自己提出问题
     * @param map 前后端传递数据
     * @return 讨论区主界面
     */
    @GetMapping("/session")
    public String sessions(Map<String, Object> map){
        List<Session> sessions = sessionService.getAllSessions();
        map.put("sessions", sessions);
        return "session/index";
    }

    @GetMapping("/session/detail")
    public String sessionDetail(@RequestParam("id") Integer id,
                           Map<String, Object> map){
        Session session = sessionService.getSession(id);
        map.put("session", session);
        return "session/detail";
    }

    @GetMapping("/session/newsession")
    public String newsession(){
        return "session/newsession";
    }

    @PostMapping("/session/newsession/add")
    public String addsession(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        sessionService.newsession(phone, title, content);
        return "redirect:/session";
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
