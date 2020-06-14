package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description junit进行对数据访问层StatisticsService的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsServiceTest extends TestCase {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void rcmdStaturesTest() {
        statisticsService.rcmdStatures();
    }

    @Test
    public void rcmdConsultsTest() {
        statisticsService.rcmdConsults();
    }
}