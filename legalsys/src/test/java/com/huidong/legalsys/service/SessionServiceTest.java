package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description junit进行对数据访问层SessionService的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SessionServiceTest extends TestCase {

    @Autowired
    private SessionService sessionService;

    @Test
    public void newsessionTest() {
        String phone = "11111111113";
        String title = "殴打至轻伤二级";
        String content = "李某的妹妹李某某与被害人华某某在桦川县悦来镇石锅烤肉吃饭时发生口角，华某某殴打李某某被他人拉开。后李某某打电话将此事告知李某。李某便开车接上李某某在悦来镇“0454饮吧”找到华某某并质问其因何殴打李某某，之后二人厮打在一起。李某用拳头、巴掌连续击打华某某脸部，致华受伤住院治疗。经桦川县公安局司法鉴定，华某某所受伤为轻伤二级。";
        sessionService.newsession(phone, title, content);
    }

    @Test
    public void getAllSessionsTest() {
        sessionService.getAllSessions();
    }

    @Test
    public void estbConvrTest() {
        String lawyerphone = "11111111114";
        Integer id = 1;
        sessionService.estbConvr(lawyerphone, id);
    }
}