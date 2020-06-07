package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.ConsultService;
import com.huidong.legalsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Description 法律条文检索的控制层
 */
@Controller
public class PenallawController {

    @Autowired
    private ConsultService consultService;

    @GetMapping("/penallaw")
    public String penallaw(Map<String, Object> map) {
        List<Stature> statures = consultService.getAllStatures();
        map.put("statures", statures);
        return "penallaw/index";
    }

    /**
     * @Description 用户点击相关法条，系统展示法条内容
     * @param lawid 法条编号
     * @return
     */
    @GetMapping("/penallaw/detail")
    public String getlaw(@RequestParam("lawid") Integer lawid,
                         Map<String, Object> map) {
        Stature stature = consultService.getStature(lawid);
        map.put("stature", stature);
        return "penallaw/detail";
    }
}
