package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class ConsultController {

    @Autowired
    private ConsultService consultService;


    @GetMapping("/consult/detail")
    public String consultDetail(@RequestParam("id") Integer id,
                                Map<String, Object> map) {
        Consult consult = consultService.getConsult(id);
        map.put("consult", consult);
        return "consult/detail";
    }

    @GetMapping("/consult")
    public String consult() {
        return "consult/index";
    }

    @GetMapping("/consult/result")
    public String consultResult(Map<String, Object> map,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        String query = (String) session.getAttribute("query");
        String result = (String) session.getAttribute("result");
        map.put("query", query);
        map.put("result", result);
        session.removeAttribute("query");
        session.removeAttribute("result");
        return "consult/result";
    }


    @PostMapping("/consult/upload")
    public String consultUpload(@RequestParam("title") String title,
                                @RequestParam("query") String query,
                                HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        String result = consultService.newconsult(phone, title, query);
        session.setAttribute("query", query);
        session.setAttribute("result", result);
        return "redirect:/consult/result";
    }
}
