package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Description 统计可视化的控制层
 */
@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * @Description 展示系统推荐的法条和案件，统计在线人数，用户未登录
     * @return 系统的主界面
     */
    @GetMapping("/")
    public String statisticsGuest(Map<String, Object> map){
        ArrayList<Stature> rcmdStatures = (ArrayList<Stature>) statisticsService.rcmdStatures();
        map.put("rcmdStatures", rcmdStatures);

        ArrayList<Consult> rcmdConsults = (ArrayList<Consult>) statisticsService.rcmdConsults();
        map.put("rcmdConsults", rcmdConsults);

        Integer concurrentNum = statisticsService.showConcurrent();
        map.put("concurrentNum", concurrentNum);
        return "statisticsGuest";
    }

    /**
     * @Description 展示系统推荐的法条和案件，统计在线人数
     * @return 系统的主界面
     */
    @GetMapping("/logined")
    public String statisticsUser(Map<String, Object> map){
        ArrayList<Stature> rcmdStatures = (ArrayList<Stature>) statisticsService.rcmdStatures();
        map.put("rcmdStatures", rcmdStatures);

        ArrayList<Consult> rcmdConsults = (ArrayList<Consult>) statisticsService.rcmdConsults();
        map.put("rcmdConsults", rcmdConsults);

        Integer concurrentNum = statisticsService.showConcurrent();
        map.put("concurrentNum", concurrentNum);
        return "statisticsUser";
    }
}
