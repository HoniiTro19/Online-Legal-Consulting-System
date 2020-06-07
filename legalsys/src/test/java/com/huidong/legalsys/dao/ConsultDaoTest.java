package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Consult;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description junit进行对数据访问层ConsultDao的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsultDaoTest extends TestCase {

    @Autowired
    private ConsultDao consultDao;

    @Test
    public void newConsultTest() {
        Consult consult = new Consult();
        consult.setPhone("11111111111");
        consult.setTitle("殴打至轻伤二级");
        consult.setQuery("昌宁县人民检察院指控，2014年4月19日下午16时许，被告人段某驾拖车经过鸡飞乡澡塘街子，时逢堵车，段某将车停在“冰凉一夏”冷饮店门口，被害人王某的侄子王2某示意段某靠边未果，后上前敲打车门让段某离开，段某遂驾车离开，但对此心生怨愤。同年4月21日22时许，被告人段某酒后与其妻子王1某一起准备回家，走到鸡飞乡澡塘街富达通讯手机店门口时停下，段某进入手机店内对被害人王某进行吼骂，紧接着从手机店出来拿得一个石头又冲进手机店内朝王某头部打去，致王某右额部粉碎性骨折、右眼眶骨骨折。经鉴定，被害人王某此次损伤程度为轻伤一级。");
        consult.setType(0);
        consult.setResult("234");
        consult.setTime(" ");
        Boolean isSuccess = consultDao.newConsult(consult);
        assertTrue("未能成功新建咨询记录", isSuccess);
    }

    @Test
    public void getConsultsByPhoneTest() {
        String phone = "15190218902";
        List<Consult> consults = consultDao.getConsultsByPhone(phone);
        assertNotNull("未能成功获得用户的咨询记录", consults);
    }

    @Test
    public void deleteConsultTest() {
        Integer id = 1;
        Boolean isSuccess = consultDao.deleteConsult(id);
        assertTrue("未能成功删除咨询记录", isSuccess);
    }

    @Test
    public void getTopkConsultsTest() {
        List<Consult> consults = consultDao.getTopkConsults();
        assertNotNull("未能成功获得点击数前k的咨询记录", consults);
    }

    @Test
    public void getAllConsultLogsTest() {
        List<Consult> consults = consultDao.getAllConsults();
        assertNotNull("未能成功获得所有咨询记录", consults);
    }
}