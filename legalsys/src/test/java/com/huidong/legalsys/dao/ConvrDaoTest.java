package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Convr;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConvrDaoTest extends TestCase {

    @Autowired
    private ConvrDao convrDao;

    @Test
    public void newConvrLogTest() {
        Convr convr = new Convr();
        convr.setPhone("15190218902");
        convr.setLawyerphone("15190218905");
        convr.setConvr("你好！");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        convr.setTime(timenow);
        Boolean isSuccess = convrDao.newConvr(convr);
        if (!isSuccess)
            System.out.println("newConvrLogTest:\n" + "new convrLog failed ");
        else
            System.out.println("newConvrLogTest:\n" + "new convrLog " + convr);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrTest() {
        String convr = convrDao.getConvr(1);
        if (convr == null)
            System.out.println("getConvrTest:\n" + "failed");
        else
            System.out.println("getConvrTest:\n" + convr);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setConvrTest() {
        Boolean isSuccess = convrDao.setConvr("你好！" + "朋友！", 1);
        if (!isSuccess)
            System.out.println("addConvrTest:\n" + "add convr failed ");
        else
            System.out.println("addConvrTest:\n" + "add convr succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getLawyerConvrTest() {
        String lawyerconvr = convrDao.getLawyerConvr(1);
        if (lawyerconvr == null)
            System.out.println("getLawyerConvrTest:\n" + "failed");
        else
            System.out.println("getLawyerConvrTest:\n" + lawyerconvr);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setLawyerConvrTest() {
        Boolean isSuccess = convrDao.setLawyerConvr("请问有什么可以帮到您的吗", 1);
        if (!isSuccess)
            System.out.println("addLawyerConvrTest:\n" + "add lawyerconvr failed ");
        else
            System.out.println("addLawyerConvrTest:\n" + "add lawyerconvr succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getTimeTest() {
        String time = convrDao.getTime(1);
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
        Boolean isSuccess = convrDao.setTime(timenow, 1);
        if (!isSuccess)
            System.out.println("addTimeTest:\n" + "failed ");
        else
            System.out.println("addTimeTest:\n" + "succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getLawyerTimeTest() {
        String time = convrDao.getLawyerTime(1);
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
        Boolean isSuccess = convrDao.setLawyerTime(timenow, 1);
        if (!isSuccess)
            System.out.println("addLawyerTimeTest:\n" + "failed ");
        else
            System.out.println("addLawyerTimeTest:\n" + "succeed");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrLogsByPhoneTest(){
        List<Convr> convrs = convrDao.getConvrsByPhone("15190218902");
        System.out.println("getConvrLogsByPhoneTest:\n");
        for (Convr convr : convrs) {
            System.out.println(convr);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConvrLogsByLawyerPhoneTest(){
        List<Convr> convrs = convrDao.getConvrsByLawyerPhone("15190218903");
        System.out.println("getConvrLogsByLawyerPhoneTest:\n");
        for (Convr convr : convrs) {
            System.out.println(convr);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getALlConvrLogsTest(){
        List<Convr> convrs = convrDao.getAllConvrs();
        System.out.println("getALlConvrLogsTest:\n");
        for (Convr convr : convrs) {
            System.out.println(convr);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}