package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.ConsultService;
import com.huidong.legalsys.service.PenallawService;
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
    @Autowired
    private PenallawService penallawService;

    /**
     * @Description 查看刑法内容
     * @param map 前后端传递数据
     * @return 刑法内容展示的界面
     */
    @GetMapping("/penallaw")
    public String penallaw(Map<String, Object> map) {
        List<Stature> statures = penallawService.getAllStatures();
        map.put("statures", statures);
        return "penallaw/index";
    }

    /**
     * @Description 用户点击相关法条，系统展示法条详情
     * @param lawid 法条编号
     * @return 展示法条详情的界面
     */
    @GetMapping("/penallaw/detail")
    public String getlaw(@RequestParam("lawid") Integer lawid,
                         Map<String, Object> map) {
        Stature stature = penallawService.getStature(lawid);
        map.put("stature", stature);
        return "penallaw/detail";
    }
}
