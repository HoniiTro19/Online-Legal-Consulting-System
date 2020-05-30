package com.huidong.legalsys.dao;

/*
 * junit进行对数据访问层UserDao的单元测试
 */

import com.huidong.legalsys.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void isRegistedTest(){
        String phone = "15190218902";
        User user = userDao.isRegisted(phone);
        System.out.println("isRegistedTest:\n" + user);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void registerTest(){
        User newUser = new User();
        newUser.setPhone("15190218903");
        newUser.setName("张惠西");
        newUser.setPassword("222222");
        newUser.setIdno("320283199903064810");
        userDao.register(newUser);
        System.out.println("registerTest:\n" + "welcome new user " + newUser);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void loginTest(){
        String phone = "15190218902";
        String password = "111111";
        User admin = userDao.login(phone, password);
        if (admin != null)
            System.out.println("loginTest:\n" + "admin " + admin);
        else
            System.out.println("loginTest:\n" + "login failuer");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void changePasswordTest(){
        String phone = "15190218902";
        String newpassword = "222222";
        userDao.changePassword(phone, newpassword);
        System.out.println("changePasswordTest:\n" + "new password ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void uploadLicenseurlTest(){
        String phone = "15190218903";
        String lincenseurl = "url1";
        userDao.uploadLicenseurl(phone, lincenseurl);
        System.out.println("uploadLicenseurlTest:\n" + "new licenseurl ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void uploadFirmnameTest(){
        String phone = "15190218903";
        String firmname = "天津市司法局";
        userDao.uploadFirmname(phone, firmname);
        System.out.println("uploadFirmnameTest:\n" + "new firmname ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllUsersTest(){
        List<User> allusers = userDao.getAllUsers();
        System.out.println("getAllUsersTest:\n");
        for (User user : allusers){
            System.out.println(user + "\n");
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}