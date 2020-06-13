package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Session;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description  junit进行对数据访问层SessionDao的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SessionDaoTest extends TestCase {

    @Autowired
    private SessionDao sessionDao;

    @Test
    public void newSessionTest() {
        Session session = new Session();
        session.setPhone("11111111111");
        session.setTitle("殴打至轻伤二级");
        session.setContent("李某的妹妹李某某与被害人华某某在桦川县悦来镇石锅烤肉吃饭时发生口角，华某某殴打李某某被他人拉开。后李某某打电话将此事告知李某。李某便开车接上李某某在悦来镇“0454饮吧”找到华某某并质问其因何殴打李某某，之后二人厮打在一起。李某用拳头、巴掌连续击打华某某脸部，致华受伤住院治疗。经桦川县公安局司法鉴定，华某某所受伤为轻伤二级。");
        session.setStatus(1);
        Boolean isSuccess = sessionDao.newSession(session);
        assertTrue("未能成功建立新的讨论区咨询", isSuccess);
    }

    @Test
    public void isExistTest() {
        Integer id = 1;
        Integer sessionid = sessionDao.isExist(id);
        assertEquals("该讨论区咨询不存在", id, sessionid);
    }

    @Test
    public void getSessionInfoTest() {
        Integer id = 1;
        Session session = sessionDao.getSessionInfo(id);
        assertNotNull("未能成功获取咨询者的手机号", session);
    }

    @Test
    public void getPhoneTest() {
        Integer id = 1;
        String phone = sessionDao.getPhone(id);
        assertNotNull("未能成功获取咨询者的手机号", phone);
    }

    @Test
    public void getContentTest() {
        Integer id = 1;
        String content = sessionDao.getContent(id);
        assertNotNull("未能成功获取咨询的内容", content);
    }

    @Test
    public void setStatusTest() {
        Integer id = 1;
        Integer status = 1;
        Boolean isSuccess = sessionDao.setStatus(id, status);
        assertTrue("未能成功设置咨询的状态", isSuccess);
    }

    @Test
    public void getALlSessionsTest(){
        List<Session> sessions = sessionDao.getAllSessions();
        assertNotNull("未能成功获得所有未建立会话的咨询信息", sessions);
    }
}