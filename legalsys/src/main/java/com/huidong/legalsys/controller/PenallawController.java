package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 法律条文检索的控制层
 */
@Controller
public class PenallawController {

    @Autowired
    private ConsultService consultService;

    /**
     * @Description 用户点击相关法条，系统展示发条内容
     * @param lawid 法条编号
     * @return Stature 法条内容
     */
    @GetMapping("/penallaw")
    @ResponseBody
    public Stature penallaw(@RequestParam("lawid") Integer lawid){
        Stature law = consultService.getStature(lawid);
        return law;
    }
}
