package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Consult;
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
public class ConsultControllerDaoTest extends TestCase {

    @Autowired
    private ConsultDao consultDao;
    @Value("#{systemProperties['config.topk']}")
    private Integer topk;

    @Test
    public void newConsultLogTest() {
        Consult consult = new Consult();
        consult.setPhone("15190218902");
        consult.setQuery("昌宁县人民检察院指控，2014年4月19日下午16时许，被告人段某驾拖车经过鸡飞乡澡塘街子，时逢堵车，段某将车停在“冰凉一夏”冷饮店门口，被害人王某的侄子王2某示意段某靠边未果，后上前敲打车门让段某离开，段某遂驾车离开，但对此心生怨愤。同年4月21日22时许，被告人段某酒后与其妻子王1某一起准备回家，走到鸡飞乡澡塘街富达通讯手机店门口时停下，段某进入手机店内对被害人王某进行吼骂，紧接着从手机店出来拿得一个石头又冲进手机店内朝王某头部打去，致王某右额部粉碎性骨折、右眼眶骨骨折。经鉴定，被害人王某此次损伤程度为轻伤一级。");
        consult.setType(0);
        consult.setResult("234");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timenow = simpleDateFormat.format(date);
        consult.setTime(timenow);
        consult.setStatus(0);
        Boolean isSuccess = consultDao.newConsult(consult);
        if (!isSuccess)
            System.out.println("newConsultLogTest:\n" + "new consultLog failed ");
        else
            System.out.println("newConsultLogTest:\n" + "new consultLog " + consult);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConsultLogsByPhoneTest() {
        String phone = "15190218902";
        List<Consult> consults = consultDao.getConsultsByPhone(phone);
        System.out.println("getConsultLogsByPhoneTest:\n");
        for (Consult consult : consults){
            System.out.println(consult);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConsultLogsByTypeTest() {
        Integer type = 1;
        List<Consult> consults = consultDao.getConsultsByType(type);
        System.out.println("getConsultLogsByTypeTest:\n");
        for (Consult consult : consults){
            System.out.println(consult);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getTopkConsultLogsTest() {
        List<Consult> consults = consultDao.getTopkConsults();
        System.out.println("getTopkConsultLogsTest:\n");
        for (Consult consult : consults) {
            System.out.println(consult);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllConsultLogsTest() {
        List<Consult> consults = consultDao.getAllConsults();
        System.out.println("getAllConsultLogsTest:\n");
        for (Consult consult : consults) {
            System.out.println(consult);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}