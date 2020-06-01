package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.ConvrLog;
import com.huidong.legalsys.domain.Session;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SessionDaoTest extends TestCase {

    @Autowired
    private SessionDao sessionDao;

    @Test
    public void newSessionTest() {
        Session session = new Session();
        session.setPhone("15190218902");
        session.setTitle("殴打至轻伤二级");
        session.setContent("李某的妹妹李某某与被害人华某某在桦川县悦来镇石锅烤肉吃饭时发生口角，华某某殴打李某某被他人拉开。后李某某打电话将此事告知李某。李某便开车接上李某某在悦来镇“0454饮吧”找到华某某并质问其因何殴打李某某，之后二人厮打在一起。李某用拳头、巴掌连续击打华某某脸部，致华受伤住院治疗。经桦川县公安局司法鉴定，华某某所受伤为轻伤二级。");
        session.setStatus(1);
        Boolean isSuccess = sessionDao.newSession(session);
        if (!isSuccess)
            System.out.println("newSessionTest:\n" + "new session failed ");
        else
            System.out.println("newSessionTest:\n" + "new session " + session);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setStatusTest() {
        Boolean isSuccess = sessionDao.setStatus(0, 1);
        if (!isSuccess)
            System.out.println("setStatusTest:\n" + "set status failed ");
        else
            System.out.println("setStatusTest:\n" + "set status succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getALlSessionsTest(){
        List<Session> sessions = sessionDao.getAllSessions();
        System.out.println("getALlSessionsTest:\n");
        for (Session session : sessions) {
            System.out.println(session);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}