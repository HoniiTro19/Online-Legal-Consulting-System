package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.ConsultLog;
import com.huidong.legalsys.domain.ConvrLog;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConvrLogDaoTest extends TestCase {

    @Autowired
    private ConvrLogDao convrLogDao;

    @Test
    public void isestablishedTest(){
        String phone = "15190218902";
        String lawyerphone = "15190218903";
        ConvrLog convrLog = convrLogDao.isestablished(phone, lawyerphone);
        if (convrLog == null)
            System.out.println("isestablishedTest:\n" + "not established ");
        else
            System.out.println("newConsultLogTest:\n" + "established " + convrLog);

    }

    @Test
    public void newConvrLogTest() {
        ConvrLog convrLog = new ConvrLog();
        convrLog.setPhone("15190218902");
        convrLog.setLawyerphone("15190218905");
        convrLog.setConvr("你好！");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        convrLog.setTime(timenow);
        Boolean isSuccess = convrLogDao.newConvrLog(convrLog);
        if (!isSuccess)
            System.out.println("newConvrLogTest:\n" + "new convrLog failed ");
        else
            System.out.println("newConvrLogTest:\n" + "new convrLog " + convrLog);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrTest() {
        String convr = convrLogDao.getConvr("15190218902", "15190218903");
        if (convr == null)
            System.out.println("getConvrTest:\n" + "failed");
        else
            System.out.println("getConvrTest:\n" + convr);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setConvrTest() {
        Boolean isSuccess = convrLogDao.setConvr("你好！" + "朋友！", "15190218902", "15190218903");
        if (!isSuccess)
            System.out.println("addConvrTest:\n" + "add convr failed ");
        else
            System.out.println("addConvrTest:\n" + "add convr succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getLawyerConvrTest() {
        String lawyerconvr = convrLogDao.getLawyerConvr("15190218902", "15190218903");
        if (lawyerconvr == null)
            System.out.println("getLawyerConvrTest:\n" + "failed");
        else
            System.out.println("getLawyerConvrTest:\n" + lawyerconvr);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setLawyerConvrTest() {
        Boolean isSuccess = convrLogDao.setLawyerConvr("请问有什么可以帮到您的吗", "15190218902", "15190218903");
        if (!isSuccess)
            System.out.println("addLawyerConvrTest:\n" + "add lawyerconvr failed ");
        else
            System.out.println("addLawyerConvrTest:\n" + "add lawyerconvr succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getTimeTest() {
        String time = convrLogDao.getTime("15190218902", "15190218903");
        if (time == null)
            System.out.println("getTimeTest:\n" + "failed");
        else
            System.out.println("getTimeTest:\n" + time);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setTimeTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        Boolean isSuccess = convrLogDao.setTime(timenow, "15190218902", "15190218903");
        if (!isSuccess)
            System.out.println("addTimeTest:\n" + "failed ");
        else
            System.out.println("addTimeTest:\n" + "succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getLawyerTimeTest() {
        String time = convrLogDao.getLawyerTime("15190218902", "15190218903");
        if (time == null)
            System.out.println("getLawyerTimeTest:\n" + "failed");
        else
            System.out.println("getLawyerTimeTest:\n" + time);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setLawyerTimeTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        Boolean isSuccess = convrLogDao.setLawyerTime(timenow, "15190218902", "15190218903");
        if (!isSuccess)
            System.out.println("addLawyerTimeTest:\n" + "failed ");
        else
            System.out.println("addLawyerTimeTest:\n" + "succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrLogsByPhoneTest(){
        List<ConvrLog> convrLogs = convrLogDao.getConvrLogsByPhone("15190218902");
        System.out.println("getConvrLogsByPhoneTest:\n");
        for (ConvrLog convrLog : convrLogs) {
            System.out.println(convrLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrLogsByLawyerPhoneTest(){
        List<ConvrLog> convrLogs = convrLogDao.getConvrLogsByLawyerPhone("15190218903");
        System.out.println("getConvrLogsByLawyerPhoneTest:\n");
        for (ConvrLog convrLog : convrLogs) {
            System.out.println(convrLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getALlConvrLogsTest(){
        List<ConvrLog> convrLogs = convrLogDao.getAllConvrLogs();
        System.out.println("getALlConvrLogsTest:\n");
        for (ConvrLog convrLog : convrLogs) {
            System.out.println(convrLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}