package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Session;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ConsultTypeEnum;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String consult(@RequestParam("type") Integer type,
                          @RequestParam("type") String title,
                          @RequestParam("query") String query,
                          HttpServletRequest request,
                          Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        String result = "";
        try {
            result = consultService.newconsult(phone, title, query, type);
        }catch (IOException e){
            e.printStackTrace();
        }
        if (type.equals(ConsultTypeEnum.LAW.getType())){
            //解析相关法条的ID
        }else {
            String penalty = result;
            map.put("penalty", penalty);
        }
        return "consult";
    }
}
