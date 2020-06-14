package com.huidong.legalsys.dao;

/**
 * @Description junit进行对数据访问层UserDao的单元测试
 */

import com.huidong.legalsys.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void registerTest(){
        User newUser = new User();
        newUser.setPhone("11111111112");
        newUser.setName("张惠西");
        newUser.setPassword("222222");
        newUser.setIdno("320283199903060000");
        Boolean isSuccess = userDao.register(newUser);
        assertTrue("注册失败", isSuccess);
    }

    @Test
    public void isRegistedTest(){
        String phone = "11111111111";
        String userphone = userDao.isRegisted(phone);
        assertEquals("未注册用户", phone, userphone);
    }

    @Test
    public void loginTest(){
        String phone = "11111111111";
        String password = "111111";
        User user = userDao.login(phone, password);
        assertNotNull("登录失败", user);
    }

    @Test
    public void getPasswordTest(){
        String phone = "11111111111";
        String password = userDao.getPassword(phone);
        assertNotNull("获取密码失败", password);
    }

    @Test
    public void setPasswordTest(){
        String phone = "11111111111";
        String newpassword = "222222";
        Boolean isSuccess = userDao.setPassword(phone, newpassword);
        assertTrue("设置密码失败", isSuccess);
    }

    @Test
    public void setLicenseurlTest(){
        String phone = "11111111111";
        String lincenseurl = "url1";
        Boolean isSuccess = userDao.setLicenseurl(phone, lincenseurl);
        assertTrue("上传律师执照失败", isSuccess);
    }

    @Test
    public void setFirmnameTest(){
        String phone = "11111111111";
        String firmname = "天津市司法局";
        Boolean isSuccess = userDao.setFirmname(phone, firmname);
        assertTrue("上传律所信息失败", isSuccess);
    }

    @Test
    public void getUserInfoTest(){
        User user = userDao.getUserInfo("11111111111");
        assertNotNull("获得用户详细信息失败", user);
    }

    @Test
    public void getAllUsersTest(){
        List<User> allusers = userDao.getAllUsers();
        assertNotNull("获得所有用户信息失败", allusers);
    }
}