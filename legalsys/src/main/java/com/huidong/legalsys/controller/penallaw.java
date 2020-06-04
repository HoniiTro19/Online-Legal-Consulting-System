package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class penallaw {

    @Autowired
    private ConsultService consultService;

    @GetMapping("/penallaw")
    @ResponseBody
    public Stature penallaw(@RequestParam("lawid") Integer lawid){
        Stature law = consultService.getStature(lawid);
        return law;
    }
}
