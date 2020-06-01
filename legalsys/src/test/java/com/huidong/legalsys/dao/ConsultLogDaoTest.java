package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.ConsultLog;
import com.huidong.legalsys.domain.Stature;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsultLogDaoTest extends TestCase {

    @Autowired
    private ConsultLogDao consultLogDao;
    @Value("#{systemProperties['config.topk']}")
    private Integer topk;

    @Test
    public void newConsultLogTest() {
        ConsultLog consultLog = new ConsultLog();
        consultLog.setPhone("15190218902");
        consultLog.setQuery("昌宁县人民检察院指控，2014年4月19日下午16时许，被告人段某驾拖车经过鸡飞乡澡塘街子，时逢堵车，段某将车停在“冰凉一夏”冷饮店门口，被害人王某的侄子王2某示意段某靠边未果，后上前敲打车门让段某离开，段某遂驾车离开，但对此心生怨愤。同年4月21日22时许，被告人段某酒后与其妻子王1某一起准备回家，走到鸡飞乡澡塘街富达通讯手机店门口时停下，段某进入手机店内对被害人王某进行吼骂，紧接着从手机店出来拿得一个石头又冲进手机店内朝王某头部打去，致王某右额部粉碎性骨折、右眼眶骨骨折。经鉴定，被害人王某此次损伤程度为轻伤一级。");
        consultLog.setType(0);
        consultLog.setResult("234");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        consultLog.setTime(timenow);
        consultLog.setStatus(0);
        Boolean isSuccess = consultLogDao.newConsultLog(consultLog);
        if (!isSuccess)
            System.out.println("newConsultLogTest:\n" + "new consultLog failed ");
        else
            System.out.println("newConsultLogTest:\n" + "new consultLog " + consultLog);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConsultLogsByPhoneTest() {
        String phone = "15190218902";
        List<ConsultLog> consultLogs = consultLogDao.getConsultLogsByPhone(phone);
        System.out.println("getConsultLogsByPhoneTest:\n");
        for (ConsultLog consultLog : consultLogs){
            System.out.println(consultLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConsultLogsByTypeTest() {
        Integer type = 1;
        List<ConsultLog> consultLogs = consultLogDao.getConsultLogsByType(type);
        System.out.println("getConsultLogsByTypeTest:\n");
        for (ConsultLog consultLog : consultLogs){
            System.out.println(consultLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getTopkConsultLogsTest() {
        List<ConsultLog> consultLogs = consultLogDao.getTopkConsultLogs();
        System.out.println("getTopkConsultLogsTest:\n");
        for (ConsultLog consultLog : consultLogs) {
            System.out.println(consultLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllConsultLogsTest() {
        List<ConsultLog> consultLogs = consultLogDao.getAllConsultLogs();
        System.out.println("getAllConsultLogsTest:\n");
        for (ConsultLog consultLog : consultLogs) {
            System.out.println(consultLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}