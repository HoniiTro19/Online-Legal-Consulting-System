package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Convr;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description  junit进行对数据访问层ConvrDao的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConvrDaoTest extends TestCase {

    @Autowired
    private ConvrDao convrDao;

    @Test
    public void newConvrTest() {
        Convr convr = new Convr();
        convr.setPhone("15111111111");
        convr.setLawyerphone("11111111111");
        convr.setConvr("你好！");
        convr.setTime("");
        Boolean isSuccess = convrDao.newConvr(convr);
        assertTrue("未能成功建立会话", isSuccess);
    }

    @Test
    public void getConvrInfoTest() {
        Integer id = 1;
        Convr convr = convrDao.getConvrInfo(id);
        assertNotNull("未能成功得到普通用户的聊天内容", convr);
    }

    @Test
    public void getConvrTest() {
        Integer id = 1;
        String convr = convrDao.getConvr(id);
        assertNotNull("未能成功得到普通用户的聊天内容", convr);
    }

    @Test
    public void setConvrTest() {
        String record = "你好！" + "朋友！";
        Integer id = 1;
        Boolean isSuccess = convrDao.setConvr(record, id);
        assertTrue("未能成功设置普通用户的聊天内容", isSuccess);
    }

    @Test
    public void getTimeTest() {
        Integer id = 1;
        String time = convrDao.getTime(id);
        assertNotNull("未能成功得到普通用户的聊天时间", time);
    }

    @Test
    public void setTimeTest() {
        Integer id = 1;
        String timenow = " ";
        Boolean isSuccess = convrDao.setTime(timenow, id);
        assertTrue("未能成功设置普通用户的聊天时间", isSuccess);
    }

    @Test
    public void getConvrsTest() {
        List<Convr> convrs = convrDao.getConvrs("15111111111");
        assertNotNull("未能成功得到普通用户的会话信息", convrs);
    }


    @Test
    public void getALlConvrsTest() {
        List<Convr> convrs = convrDao.getAllConvrs();
        assertNotNull("未能成功得到所有会话信息", convrs);
    }
}