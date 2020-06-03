package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/")
    public String statistics(Map<String, Object> map){
        ArrayList<Stature> rcmdStatures = (ArrayList<Stature>) statisticsService.rcmdStatures();
        map.put("rcmdStatures", rcmdStatures);

        ArrayList<Consult> rcmdConsults = (ArrayList<Consult>) statisticsService.rcmdConsults();
        map.put("rcmdConsults", rcmdConsults);

        Integer concurrentNum = statisticsService.showConcurrent();
        map.put("concurrentNum", concurrentNum);
        return "statistics";
    }
}
