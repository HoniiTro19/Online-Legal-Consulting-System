package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description junit进行对数据访问层ConsultService的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsultServiceTest extends TestCase {

    @Autowired
    private ConsultService consultService;

    @Test
    public void getStatureTest() {
        Integer lawid = 105;
        consultService.getStature(lawid);
    }
}