package com.huidong.legalsys.dao;

/**
 * @Description  junit进行对数据访问层StatureDao的单元测试
 */

import com.huidong.legalsys.domain.Stature;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatureDaoTest extends TestCase {

    @Autowired
    private StatureDao statureDao;

    @Test
    public void addClickrateTest(){
        Integer lawid = 100;
        Boolean isSuccess = statureDao.addClickrate(lawid);
        assertTrue("增加点击数失败", isSuccess);
    }

    @Test
    public void getStatureTest(){
        Integer lawid = 500;
        Stature stature = statureDao.getStatureInfo(lawid);
        assertNull("查询不到对应法条", stature);
    }

    @Test
    public void getAllStaturesTest(){
        List<Stature> statures = statureDao.getAllStatures();
        assertNotNull("查询不到法条", statures);
    }

    @Test
    public void getTopkStaturesTest(){
        List<Stature> statures = statureDao.getTopkStatures();
        assertNotNull("查询不到法条", statures);
    }
}