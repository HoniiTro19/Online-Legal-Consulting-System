package com.huidong.legalsys.controller;

import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @GetMapping("/consult")
    public String consult(@RequestParam
                          @RequestParam("type") Integer type,
                          @RequestParam("query") String query){
        String result = consultService.newconsult()
        return "/consult";
    }
}
